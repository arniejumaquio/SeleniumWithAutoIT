#RequireAdmin
; ----------------------------------------------------------------------------
;
; AutoIt Version: 3.1.1
; Author:         Ejoc/JPM/Jon
;
; Script Function:
; Toggle AutoIt beta.
;
; ----------------------------------------------------------------------------

;~ #include <MsgBoxConstants.au3>
Global Const $MB_SYSTEMMODAL = 4096 ; System modal : window has an icon

;~ #include <WinAPIReg.au3>

; to avoid big include WinAPIReg.au3
Global Const $HKEY_CURRENT_USER = 0x80000001
Func _WinAPI_RegDeleteTreeEx($hKey, $sSubKey = 0)
	Local $sSubKeyType = 'wstr'
	If Not IsString($sSubKey) Then $sSubKeyType = 'ptr'

	Local $aCall = DllCall('advapi32.dll', 'long', 'RegDeleteTreeW', 'handle', $hKey, $sSubKeyType, $sSubKey)
	If @error Then Return SetError(@error, @extended, 0)
	If $aCall[0] Then Return SetError(10, $aCall[0], 0)

	Return 1
EndFunc   ;==>_WinAPI_RegDeleteTreeEx

Local $sInstallDir = RegRead("HKLM\Software\AutoIt v3\AutoIt", "InstallDir")
If $sInstallDir = "" Then $sInstallDir = RegRead("HKLM\Software\Wow6432Node\AutoIt v3\AutoIt", "InstallDir")
If $sInstallDir = "" Then
	MsgBox($MB_SYSTEMMODAL, 'Error', 'Cannot find AutoIt Installation directory')
	Exit
EndIf

Const $sKey = "HKCR\.au3"
Const $sUserChoiceKey = "Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts"
Local $sCurrentAssoc = RegRead($sKey, "")

; Nuke the UserChoice key which interferes
;~ RegDelete("HKCU\" & $sUserChoiceKey & "\.au3\UserChoice") ; RegDelete() does not work on that key
_WinAPI_RegDeleteTreeEx($HKEY_CURRENT_USER, $sUserChoiceKey & "\.au3\UserChoice")
_WinAPI_RegDeleteTreeEx($HKEY_CURRENT_USER, $sUserChoiceKey & "\.a3x\UserChoice")

If RegRead("HKCU\" & $sUserChoiceKey & "\.au3\UserChoice", "ProgID") <> "" Then
	MsgBox($MB_SYSTEMMODAL, 'Error', "Unable to change the association due to OS restrictions. Please right-click a script and select 'Open with \ Choose default program'.")
	Exit
EndIf

If $sCurrentAssoc = "AutoIt3ScriptBeta" Then
	; Already using beta switch to prod
	RegWrite($sKey, "", "REG_SZ", "AutoIt3Script")
	; make sure that the right AutoItX.dll is installed
	RunWait('regsvr32 /s "' & $sInstallDir & '\AutoItX\AutoItX3.dll"')
	MsgBox($MB_SYSTEMMODAL, "Beta Toggle", "Now using RELEASE version v" & FileGetVersion($sInstallDir & '\AutoIt3.exe') & " of AutoIt", 2)
ElseIf $sCurrentAssoc = "AutoIt3Script" Then
	; Using prod, change to beta
	RegWrite($sKey, "", "REG_SZ", "AutoIt3ScriptBeta")
	; make sure that the right AutoItX.dll is installed
	RunWait('regsvr32 /s "' & $sInstallDir & '\beta\AutoItX\AutoItX3.dll"')
	MsgBox($MB_SYSTEMMODAL, "Beta Toggle", "Now using BETA version v" & FileGetVersion($sInstallDir & '\Beta\AutoIt3.exe') & " of AutoIt", 2)
Else
	MsgBox($MB_SYSTEMMODAL, "Beta Toggle", "AutoIt installation appears to be customised - please manually edit file associations.")
EndIf
