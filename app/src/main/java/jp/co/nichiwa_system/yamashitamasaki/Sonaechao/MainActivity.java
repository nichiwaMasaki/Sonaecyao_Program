package jp.co.nichiwa_system.yamashitamasaki.Sonaechao;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import  com.google.android.gms.ads.AdRequest;
import  com.google.android.gms.ads.AdView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<LinearLayout>{

    final int MAX_HIJOUSYOKU = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_main.xmlを使う場合これを宣言する
        setContentView(R.layout.activity_main);

        //TextView day = (TextView)findViewById(R.id.TextDay);    //文字を表示させるやつ
        Calendar cl = Calendar.getInstance();       //今日の日付の取得

        //RelativeLayout rl = (RelativeLayout)findViewById(R.id.Relative);

        Button DispBtn = (Button)findViewById(R.id.settingbutton);//「設定」ボタン
        Button Home = (Button)findViewById(R.id.home);//「ホーム」ボタン
        Button Stock = (Button)findViewById(R.id.bichiku);//「備蓄」ボタン
        Button hijousyoku = (Button)findViewById(R.id.hijousyoku);//「非常食」ボタン

        ImageButton hijousyoku_ib = (ImageButton)findViewById(R.id.L_graph);
        ImageButton bichiku_ib = (ImageButton)findViewById(R.id.R_graph);

        //非常食へ
        hijousyoku.setOnClickListener(new OnClickListenerClass(".Hijousyoku", this));
        hijousyoku_ib.setOnClickListener(new OnClickListenerClass(".Hijousyoku",this));

        //備蓄品へ
        Stock.setOnClickListener(new OnClickListenerClass(".Stock", this));
        bichiku_ib.setOnClickListener( new OnClickListenerClass(".Stock",this) );
        //設定画面へ
        DispBtn.setOnClickListener( new OnClickListenerClass(".SubActivity",this ) );

        SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                //各グラフの取得
        ImageButton R_button = (ImageButton)findViewById(R.id.R_graph);
        ImageButton L_button = (ImageButton)findViewById(R.id.L_graph);
        int[] goukei = new int[2];

        //非常食の割合を取得
        goukei[0] = (int)eiyou();

        //左グラフの画像
        if( goukei[0] < 0 )
        {
            L_button.setImageResource(R.drawable.l_graph0);
        }
        else if( 0 < goukei[1] && goukei[1] < 10 )
        {
            L_button.setImageResource(R.drawable.l_graph1);
        }
        else if( 10 < goukei[1] && goukei[1] < 20 )
        {
            L_button.setImageResource(R.drawable.l_graph2);
        }
        else if( 20 < goukei[1] && goukei[1] < 30 )
        {
            L_button.setImageResource(R.drawable.l_graph3);
        }
        else if( 30 < goukei[1] && goukei[1] < 40 )
        {
            L_button.setImageResource(R.drawable.l_graph4);
        }
        else if( 40 < goukei[1] && goukei[1] < 50 )
        {
            L_button.setImageResource(R.drawable.l_graph5);
        }
        else if( 50 < goukei[1] && goukei[1] < 60 )
        {
            L_button.setImageResource(R.drawable.l_graph6);
        }
        else if( 60 < goukei[1] && goukei[1] < 70 )
        {
            L_button.setImageResource(R.drawable.l_graph7);
        }
        else if( 70 < goukei[1] && goukei[1] < 80 )
        {
            L_button.setImageResource(R.drawable.l_graph8);
        }
        else if( 80 < goukei[1] && goukei[1] < 90 )
        {
            L_button.setImageResource(R.drawable.l_graph9);
        }
        else if( 90 < goukei[1] && goukei[1] < 100 )
        {
            L_button.setImageResource(R.drawable.l_graph10);
        }
        else if( 100 < goukei[1] && goukei[1] < 110 )
        {
            L_button.setImageResource(R.drawable.l_graph11);
        }
        else if( 110 < goukei[1] )
        {
            L_button.setImageResource(R.drawable.l_graph12);
        }

        //グラフのパーセント値を表示する
        ((TextView)findViewById(R.id.hijousyoku_percent)).setText(String.valueOf(goukei[0]) + "%");

        //防犯グッズの値
        goukei[1] = 50;
                /*
                pref.getInt("retorutogohan_number",0) +
                pref.getInt("kandume_number",0) +
                pref.getInt("kanmen_number",0) +
                pref.getInt("kanpan_number",0) +
                pref.getInt("kandume2_number",0) +
                pref.getInt("retoruto_number",0) +
                pref.getInt("furizu_dorai_number",0) +
                pref.getInt("mizu_number",0) +
                pref.getInt("karori_meito_number",0) +
                pref.getInt("okasi_number",0) +
                pref.getInt("rinyu_number",0) +
                pref.getInt("konamilk_number",0);
                */


        //右グラフの画像
        if( goukei[1] < 0 )
        {
            R_button.setImageResource(R.drawable.r_graph0);
        }
        else if( 0 < goukei[1] && goukei[1] < 10 )
        {
            R_button.setImageResource(R.drawable.r_graph1);
        }
        else if( 10 < goukei[1] && goukei[1] < 20 )
        {
            R_button.setImageResource(R.drawable.r_graph2);
        }
        else if( 20 < goukei[1] && goukei[1] < 30 )
        {
            R_button.setImageResource(R.drawable.r_graph3);
        }
        else if( 30 < goukei[1] && goukei[1] < 40 )
        {
            R_button.setImageResource(R.drawable.r_graph4);
        }
        else if( 40 < goukei[1] && goukei[1] < 50 )
        {
            R_button.setImageResource(R.drawable.r_graph5);
        }
        else if( 50 < goukei[1] && goukei[1] < 60 )
        {
            R_button.setImageResource(R.drawable.r_graph6);
        }
        else if( 60 < goukei[1] && goukei[1] < 70 )
        {
            R_button.setImageResource(R.drawable.r_graph7);
        }
        else if( 70 < goukei[1] && goukei[1] < 80 )
        {
            R_button.setImageResource(R.drawable.r_graph8);
        }
        else if( 80 < goukei[1] && goukei[1] < 90 )
        {
            R_button.setImageResource(R.drawable.r_graph9);
        }
        else if( 90 < goukei[1] && goukei[1] < 100 )
        {
            R_button.setImageResource(R.drawable.r_graph10);
        }
        else if( 100 < goukei[1] && goukei[1] < 110 )
        {
            R_button.setImageResource(R.drawable.r_graph11);
        }
        else if( 110 < goukei[1] )
        {
            R_button.setImageResource(R.drawable.r_graph12);
        }

        //グラフのパーセント値を表示する(10%ずつ)
        ((TextView) findViewById(R.id.bichiku_percent)).setText(String.valueOf(goukei[1]) + "%");

        //ダイアログの生成
        DialogClass keikoku;

        //備蓄品の割合が50%未満の場合、警告を出す
        if( goukei[0] < 50 )
        {
            keikoku = new DialogClass("警告","備蓄品が50%未満です",this);
            keikoku.setPositiveButton("確認する", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.setClassName("jp.co.nichiwa_system.yamashitamasaki.Sonaechao", "jp.co.nichiwa_system.yamashitamasaki.Sonaechao.Hijousyoku");
                    startActivity(intent);
                }
            });
            keikoku.setNegativeButton("後で",null);
            keikoku.Diarog_show();
        }

        //非常食の割合が50%未満の場合、警告を出す
        if( goukei[1] < 50 )
        {
            keikoku = new DialogClass("警告","非常食が50%未満です",this);
            keikoku.setPositiveButton("確認する", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.setClassName("jp.co.nichiwa_system.yamashitamasaki.Sonaechao", "jp.co.nichiwa_system.yamashitamasaki.Sonaechao.Hijousyoku");
                    startActivity(intent);

                }
            });
            keikoku.setNegativeButton("後で",null);
            keikoku.Diarog_show();
        }
        //要チェック
        //フラグメントのリニアレイアウトを取得
        TableLayout tl  = (TableLayout)findViewById(R.id.CheckLayout);

        //プレファレンスを生成して、設定画面のデータを取得する
        pref = getSharedPreferences("Preferences",MODE_PRIVATE);
        int gou = pref.getInt("youji_people",0) +
                pref.getInt("kobito_people",0) +
                pref.getInt("otona_people",0) +
                pref.getInt("kiniti_day",0) +
                pref.getInt("sitei_day",0);


        //設定画面の合計値(gou)が0ならば警告ダイアログをだす
        if( gou <= 0 )
        {
            //ダイアログの表示
            DialogClass dc = new DialogClass("警告！","設定画面から値を入力してください",this);
            dc.setPositiveButton("設定へ移動", new ListenerClass("jp.co.nichiwa_system.yamashitamasaki.Sonaechao", "jp.co.nichiwa_system.yamashitamasaki.Sonaechao.SubActivity", MainActivity.this) );
            dc.setNegativeButton("後で",null);
            dc.Diarog_show();
        }
        //要チェックに使用するTextViewを使用する
        TextView[] Hijousyoku_tv = new TextView[MAX_HIJOUSYOKU];
        for( int i = 0 ; i < MAX_HIJOUSYOKU ; i++ ) {
            Hijousyoku_tv[i] = new TextView(this);
        }

        //警告文を取得する
        Hijousyoku_tv[0].setText( get_Number_of_days_Warning("retorutogohan_number_pref", "レトルトご飯") );
        Hijousyoku_tv[1].setText( get_Number_of_days_Warning("kandume_number_pref", "缶詰（ご飯）") );
        Hijousyoku_tv[2].setText( get_Number_of_days_Warning("kanmen_number_pref", "乾麺") );
        Hijousyoku_tv[3].setText( get_Number_of_days_Warning("kanpan_number_pref", "カンパン") );
        Hijousyoku_tv[4].setText( get_Number_of_days_Warning("kandume2_number_pref", "缶詰（肉・魚）") );
        Hijousyoku_tv[5].setText( get_Number_of_days_Warning("retoruto_number_pref", "レトルト食品") );
        Hijousyoku_tv[6].setText( get_Number_of_days_Warning("furizu_dorai_number_pref", "フリーズドライ") );
        Hijousyoku_tv[7].setText( get_Number_of_days_Warning("mizu_number_pref", "水") );
        Hijousyoku_tv[8].setText( get_Number_of_days_Warning("karori_meito_number_pref", "カロリーメイト") );
        Hijousyoku_tv[9].setText(get_Number_of_days_Warning("okasi_number_pref", "お菓子"));
        Hijousyoku_tv[10].setText(get_Child_Warning("rinyu_number"));
        Hijousyoku_tv[11].setText(get_Child_Warning("konamilk_number"));

        //警告文を押すとダイアログが表示されるようにする
        Hijousyoku_tv[0].setOnClickListener(new DialogOnClickListenerClass("レトルトごはん", "retorutogohan_number", R.drawable.retoruto_gohan, true, this));
        Hijousyoku_tv[1].setOnClickListener(new DialogOnClickListenerClass("缶詰（ごはん）", "kandume_number", R.drawable.kandume, true, this ) );
        Hijousyoku_tv[2].setOnClickListener(new DialogOnClickListenerClass("乾麺", "kanmen_number", R.drawable.kanmen, true, this) );
        Hijousyoku_tv[3].setOnClickListener(new DialogOnClickListenerClass("カンパン", "kanpan_number", R.drawable.kanpan, true, this));
        Hijousyoku_tv[4].setOnClickListener(new DialogOnClickListenerClass("缶詰（肉・魚）", "kandume2_number", R.drawable.kandume, true, this));
        Hijousyoku_tv[5].setOnClickListener(new DialogOnClickListenerClass("レトルト食品", "retoruto_number", R.drawable.retoruto, true, this));
        Hijousyoku_tv[6].setOnClickListener(new DialogOnClickListenerClass("フリーズドライ", "furizu_dorai_number", R.drawable.furizu_dorai, true, this));
        Hijousyoku_tv[7].setOnClickListener(new DialogOnClickListenerClass("水", "mizu_number", R.drawable.mizu, true, this));
        Hijousyoku_tv[8].setOnClickListener(new DialogOnClickListenerClass("カロリーメイト", "karori_meito_number", R.drawable.karori_meito, true, this));
        Hijousyoku_tv[9].setOnClickListener(new DialogOnClickListenerClass("お菓子", "okasi_number", R.drawable.okasi, true, this));
        Hijousyoku_tv[10].setOnClickListener(new DialogOnClickListenerClass("離乳食", "rinyu_number", R.drawable.rinyu, true, this));
        Hijousyoku_tv[11].setOnClickListener(new DialogOnClickListenerClass("粉ミルク", "konamilk_number", R.drawable.konamilk, true, this));
        //getLoaderManager().initLoader(0, null, this);

        ImageView Icon_iv = new ImageView(this);
        Icon_iv.setImageResource(R.drawable.batsu);

        //画面に表示する
        for( int i = 0 ; i < MAX_HIJOUSYOKU ; i++ ) {
            //警告文を挿入する
            if( Hijousyoku_tv[i].length() > 0 ) {
                Hijousyoku_tv[i].setTextColor(Color.RED);
                //tl.addView(Icon_iv, );
                tl.addView(Hijousyoku_tv[i]);
            }
        }

        //最終入力日
        TextView b_tv = (TextView)findViewById(R.id.bichiku_nyuuryoku);
        TextView h_tv = (TextView)findViewById(R.id.hijousyoku_nyuuryoku);

        //備蓄品の最終入力日
        pref = getSharedPreferences("Stock_pref",MODE_PRIVATE);
        cl.set( pref.getInt("year", 2000), pref.getInt("month", 1), pref.getInt("day", 1) );
        b_tv.setText( "最終入力日:" + cl.get(Calendar.YEAR) + "年" + (cl.get(Calendar.MONTH)+1) + "月" + cl.get(Calendar.DAY_OF_MONTH) + "日" );

        //非常食の最終入力日
        pref = getSharedPreferences("Hijousyoku_pref",MODE_PRIVATE);
        cl.set( pref.getInt("year", 2000), pref.getInt("month", 1), pref.getInt("day", 1) );
        h_tv.setText( "最終入力日:" + cl.get(Calendar.YEAR) + "年" + (cl.get(Calendar.MONTH)+1) + "月" + cl.get(Calendar.DAY_OF_MONTH) + "日" );

        //広告の設定
        AdView adview = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);
    }


    /*********************************************************************
    //非常食の賞味期限と設定した期日を計算して、それぞれのテキストを返す
    // prefName       ・・・ プレファレンス名
    // HijousyokuName ・・・ 非常食の名前
     *********************************************************************/
    public String get_Number_of_days_Warning( String prefName, String HijousyokuName )
    {
        String str = "";
        //残り日数を取得する
        int nokori = (int)getDate(prefName);
        //期日を取得する
        int nissu =  ( getSharedPreferences("Preferences",MODE_PRIVATE) ).getInt("kiniti_day",0);
        if( nokori <= 0 ) {
            //賞味期限が切れたら表示
            str = "・" + HijousyokuName + "の賞味期限が切れました";
        } else if(nokori == 1)  {
            //賞味期限が当日になったら表示
            str = "・" + HijousyokuName + "の賞味期限が当日です";
        }
        else if( nokori <= nissu ) {
            //賞味期限が期日に近づいたら表示
            str = "・" + HijousyokuName + "の賞味期限が" + nokori + "日前です";
        }

        return str;
    }

    public String get_Icon_Warning()
    {
        return null;
    }

    // 乳児一人以上で、なおかつ離乳食と粉ミルクが「0」のとき、警告を表示
    public String get_Child_Warning( String name )
    {
        String str = "";
        int youji = ( getSharedPreferences("Preferences",MODE_PRIVATE) ).getInt("youji_people",0);
        int rinyu = ( getSharedPreferences("Preferences",MODE_PRIVATE) ).getInt(name,0);
        int milk  = ( getSharedPreferences("Preferences",MODE_PRIVATE) ).getInt(name,0);

        if( youji >= 1  ) {
            if( rinyu >= 1 ) {
                str += "離乳食が入力されていません";
            }
        }

        return str;
    }

    /*********************************************************************
    // 賞味期限の日付と現在の日付から引き出された残り日数を返す
    // prefName ・・・　プレファレンス名
     *********************************************************************/
    public long getDate(String prefName)
    {
        SharedPreferences pref2 = getSharedPreferences(prefName,MODE_PRIVATE);
        //現在の時刻
        Calendar cl = Calendar.getInstance();
        //引数で指定した食品の賞味期限
        Calendar cl2 = Calendar.getInstance();
        cl2.set( pref2.getInt("year", cl.get(Calendar.YEAR) ), pref2.getInt("month", cl.get(Calendar.MONTH) ), pref2.getInt("day", cl.get(Calendar.DAY_OF_MONTH) ) );
        Date date1 = cl.getTime();
        Date date2 = cl2.getTime();

        long current_time = date1.getTime();
        long retorutogohan_time = date2.getTime();

        long nokori = (retorutogohan_time - current_time) / ( 1000 * 60 * 60 * 24 );

        return nokori;
    }

    /*********************************************************************
     // 人数と非常食の栄養量から割り出されたパーセンテージを返す
     // return: s_w ・・・ 非常食の全体のパーセンテージ
     *********************************************************************/
    public double eiyou() {
        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);

        int r = pref.getInt("retorutogohan_number", 0);
        int k = pref.getInt("kandume_number", 0);
        int kan = pref.getInt("kanmen_number", 0);
        int kanpan = pref.getInt("kanpan_number", 0);
        int kan2 = pref.getInt("kandume2_number", 0);
        int reto = pref.getInt("retoruto_number", 0);
        int f = pref.getInt("furizu_dorai_number", 0);
        double mizu = pref.getInt("mizu_number", 0);
        int rinyu = pref.getInt("rinyu_number", 0);
        int konamilk = pref.getInt("konamilk_number", 0);
        int karori = pref.getInt("karori_meito_number", 0);
        int okasi = pref.getInt("okasi_number", 0);

        String today_last = pref.getString("today", "まだ入力されていません。");
        String today_last_s = pref.getString("today_s", "まだ入力されていません。");


        //各合計
        //int sum = g + m + b + h + s + t + a + gun;
        int Hijousyoku_sum = r + k + kan + kanpan + kan2 + reto + f + karori + okasi + rinyu + konamilk;
        //String str = "備蓄品:"+String.valueOf(sum);
        String Hijousyoku_str2 = "非常食:" + String.valueOf(Hijousyoku_sum);
        //int hoge = setting.getInt("setting_sp",0);
        int adult_n = pref.getInt("otona_people", 0);
        int child_n = pref.getInt("kobito_people", 0);
        int baby_n = pref.getInt("youji_people", 0);
        double mizu_a, mizu_c, mizu_b;

        double food_a, food_c, food_b;
        //大人小人幼児の必要数格納用
        double MAX;
        //水の備蓄の％格納用
        double s_w = 0;
        //非常食の備蓄の％格納用
        double p;
        //非常食（大人用）の栄養価合計値
        double v;

        //栄養価の計算。乾パンとカロリーメイトを抜いたものは栄養価１．乾パン、カロリーメイトは３。
        v = ((Hijousyoku_sum - kanpan - karori) * 1) + (kanpan + karori) * 3;
        //大人の必要数計算
        food_a = v;
        if (food_a >= 3 * adult_n) {
            food_a = 3 * adult_n;
        }//←この３は必要数。大人なら３日で９．７日で２１。
        // v = 栄養量の合計値 - 大人が消費した栄養量
        v = v - food_a;
        //小人の必要数計算
        food_c = v;
        if (food_c >= 2 * child_n) {
            food_c = 2 * child_n;
        }
        //幼児の必要数計算
        //幼児の栄養価の計算。離乳食１、粉ミルク３．
        food_b = rinyu + konamilk * 3;
        if (food_b >= 3 * baby_n) {
            food_b = 3 * baby_n;
        }
        //大人、小人、幼児の必要数を計算。
        MAX = (adult_n * 3) + (child_n * 2) + (baby_n * 3);
        //備蓄は何％あるか計算。最大50％
        p = ((food_a + food_b + food_c) / MAX) * 50;

        //水の必要数の計算
        //大人の必要数
        mizu_a = mizu * 3;
        if (mizu_a > 3 * adult_n) {
            mizu_a = 3 * adult_n;
        }
        mizu = mizu * 3 - mizu_a;
        //小人の必要数
        mizu_c = mizu * 2;
        if (mizu_c > 2 * child_n) {
            mizu_c = 2 * child_n;
        }
        mizu = mizu * 2 - mizu_c;
        //幼児の必要数
        mizu_b = mizu * 2;
        if (mizu_b > 2 * baby_n) {
            mizu_b = 2 * baby_n;
        }
        //備蓄は何％あるか計算。最大50％
        s_w = ((mizu_a + mizu_c + mizu_b) / MAX) * 50;
        //非常食と水の備蓄を合計。最大100％
        s_w = s_w + p;

        return s_w;
    }

    @Override
    public Loader<LinearLayout> onCreateLoader(int id, Bundle args) {
        //ローダの開始処理
        return null;
    }

    @Override
    public void onLoadFinished(Loader<LinearLayout> loader, LinearLayout data) {
        //ローダの終了処理

    }

    @Override
    public void onLoaderReset(Loader loader) {
        //ローダのリセット処理
        Toast.makeText(this,"更新します・・・",Toast.LENGTH_LONG).show();
    }
}
