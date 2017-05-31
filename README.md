<div align="center">
<img width="172" height="61" src="http://www.amoad.com/images/logo.png">
</div>

# AMoAd SDK for Android ver 5.0.90 (preview)

- [ZIPをダウンロード](https://github.com/amoad/amoad-android-sdk/archive/v5.1-preview.zip)
- [ドキュメント](https://github.com/amoad/amoad-android-sdk/wiki)

## 更新情報

広告クリック時に LP を WebView で開く機能を追加しました。
( 対象: ネイティブ App 広告 / リストビュー広告 )

### 設定方法

`createView` メソッドまたは `renderAd` メソッドの tag に "OpenInWebView" で始まる文字列を指定すると、その広告はクリック時に WebView で LP を表示します。
*( これは暫定的な設定方法です。正式リリース版では変更される可能性があります。 )*

- [リリース履歴](https://github.com/amoad/amoad-android-sdk/releases)


## Demo
* デモ画面に遷移してsidを入力すると広告の表示ができます。
* 広告種類ことにデモ画面が用意されています。
  * ディスプレイ広告(Display AD)
  * インタースティシャル広告(Interstitial AD)
  * インフィード広告(Infeed AD)
  * ネイティブHTML広告(NativeHtml AD)
  * スクリーン広告(Screen AD)
  * ネイティブAPP広告(NativeApp AD)
  * リストビュー広告(ListView AD)

<div>
	<img src="/Images/MainActivity.png" width=130 alt="Main画面">
	<img src="/Images/FormActivity.png" width=130 alt="sid入力画面">
	<img src="/Images/DisplayActivity.png" width=130 alt="デモ画面1">
	<img src="/Images/InterstitialActivity.png" width=130 alt="デモ画面2">
	<img src="/Images/InfeedActivity.png" width=130 alt="デモ画面3">
	<img src="/Images/ScreenActivity.png" width=130 alt="デモ画面4">
</div>

## 対応環境
Android 2.3 or later

## その他
AMoAd SDKに関する技術的なお問い合わせ・ご要望は [こちら](https://github.com/amoad/amoad-ios-sdk/issues) まで 。

- [AMoAd iOS SDK](https://github.com/amoad/amoad-ios-sdk)
- [AMoAd Unity Plugin](https://github.com/amoad/amoad-unity-plugin)
- [AMoAd Cocos2d-x Module](https://github.com/amoad/amoad-cocos2dx-module)
