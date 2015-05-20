package jp.co.nichiwa_system.yamashitamasaki.Sonaechao;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

/**
 * 単にダイアログを表示させるクラス
  Created by yamashita.masaki on 2015/05/15.
 */
public class DialogClass extends AlertDialog.Builder{

    TextView tv;

    //コンストラクタ
    public DialogClass(String TitleName, String Message, Activity act)
    {
        super(act);
        setTitle(TitleName);

        //文字操作をする
        tv = new TextView(act);//テキスト用
        tv.setText(Message);

        //TextViewをダイアログで表示させる
        setView(tv);
    }

    public DialogClass(Activity act) {
        super(act);
        tv = new TextView(act);
    }

    public void setText(String str) {
        setMessage(str);
    }

    public void Diarog_show() {
        // アラートダイアログのキャンセルが可能かどうかを設定します
        setCancelable(true);
        AlertDialog alertDialog = create();
        // アラートダイアログを表示します
        alertDialog.show();
    }

}
