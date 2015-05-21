package jp.co.nichiwa_system.yamashitamasaki.Sonaechao;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;
import java.util.Date;

import jp.co.nichiwa_system.yamashitamasaki.Sonaechao.R;


public class Hijousyoku extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hijousyoku);

        // それぞれのボタンの情報を取得
        Button Home = (Button)findViewById(R.id.home);                  //「ホーム」ボタン
        Button DispBtn = (Button)findViewById(R.id.settingbutton);    //「設定」ボタン
        Button Stock = (Button)findViewById(R.id.bichiku);             //「備蓄」ボタン

        //各イメージビューの取得
        ImageView retorutogohan_iv = (ImageView)findViewById(R.id.retoruto_gohan);
        ImageView kandume_iv = (ImageView)findViewById(R.id.kandume);
        ImageView kanmen_iv = (ImageView)findViewById(R.id.kanmen);
        ImageView kanpan_iv = (ImageView)findViewById(R.id.kanpan);
        ImageView kandume2_iv = (ImageView)findViewById(R.id.kandume2);
        ImageView retoruto_iv = (ImageView)findViewById(R.id.retoruto);
        ImageView furizu_dorai_iv = (ImageView)findViewById(R.id.furizu_dorai);
        ImageView mizu_iv = (ImageView)findViewById(R.id.mizu);
        ImageView karori_meito_iv = (ImageView)findViewById(R.id.karori_meito);
        ImageView okasi_iv = (ImageView)findViewById(R.id.okasi);
        ImageView rinyu_iv = (ImageView)findViewById(R.id.rinyu);
        ImageView konamilk_iv = (ImageView)findViewById(R.id.konamilk);

        //非常食の項目を取得する
        ItemClass[] item = new ItemClass[12];
        item[0] = new ItemClass("レトルトごはん", "retorutogohan_number", R.drawable.retoruto_gohan, true,"袋", this);
        item[1] = new ItemClass("缶詰（ごはん）", "kandume_number", R.drawable.kandume_gohan, true,"缶", this);
        item[2] = new ItemClass("乾麺", "kanmen_number", R.drawable.kanmen, true,"袋", this);
        item[3] = new ItemClass("カンパン", "kanpan_number", R.drawable.kanpan, true,"缶", this);
        item[4] = new ItemClass("缶詰（肉・魚）", "kandume2_number", R.drawable.kandume, true, "缶", this);
        item[5] = new ItemClass("レトルト食品", "retoruto_number", R.drawable.retoruto, true, "袋", this);
        item[6] = new ItemClass("フリーズドライ", "furizu_dorai_number", R.drawable.furizu_dorai, true, "塊", this);
        item[7] = new ItemClass("水", "mizu_number", R.drawable.mizu, true, "ℓ",this);
        item[8] = new ItemClass("カロリーメイト", "karori_meito_number", R.drawable.karori_meito, true, "箱", this);
        item[9] = new ItemClass("お菓子", "okasi_number", R.drawable.okasi, true, "箱・袋", this);
        item[10] = new ItemClass("離乳食", "rinyu_number", R.drawable.rinyu, true, this );
        item[11] = new ItemClass("粉ミルク", "konamilk_number", R.drawable.konamilk, true, this);


        // 場所を指定する
        Home.setOnClickListener(new OnClickListenerClass(".MainActivity", this));
        Stock.setOnClickListener(new OnClickListenerClass(".Stock", this));
        DispBtn.setOnClickListener(new OnClickListenerClass(".SubActivity", this));

        //ボタンアクションの処理
        retorutogohan_iv.setOnClickListener( new DialogOnClickListenerClass(item[0]) );
        kandume_iv.setOnClickListener( new DialogOnClickListenerClass(item[1]) );
        kanmen_iv.setOnClickListener( new DialogOnClickListenerClass(item[2]) );
        kanpan_iv.setOnClickListener( new DialogOnClickListenerClass(item[3] ) );
        kandume2_iv.setOnClickListener( new DialogOnClickListenerClass(item[4]) );
        retoruto_iv.setOnClickListener( new DialogOnClickListenerClass(item[5]) );
        furizu_dorai_iv.setOnClickListener( new DialogOnClickListenerClass(item[6]) );
        mizu_iv.setOnClickListener( new DialogOnClickListenerClass( item[7] ) );
        karori_meito_iv.setOnClickListener( new DialogOnClickListenerClass(item[8] ) );
        okasi_iv.setOnClickListener( new DialogOnClickListenerClass(item[9] ) );
        rinyu_iv.setOnClickListener( new DialogOnClickListenerClass(item[10]) );
        konamilk_iv.setOnClickListener( new DialogOnClickListenerClass(item[11]) );

        if( Check_Day(item[0].prefName) ) {
            retorutogohan_iv.setBackgroundResource(R.drawable.style);
        }

        //広告の設定
        AdView adview = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);
    }

    //賞味期限を切れているかどうか判定する関数
    public boolean Check_Day( String prefName )
    {
        int i = 0;
        //残り日数を取得する
        int nokori = (int)getDate(prefName);

        if( nokori <= 0 ) {
            //賞味期限が切れたら表示
            return false;
        }
        return true;
    }

    /*********************************************************************
     // 賞味期限の日付と現在の日付から引き出された残り日数を返す
     // prefName ・・・　プレファレンス名
     *********************************************************************/
    public long getDate(String prefName)
    {
        SharedPreferences pref2 = getSharedPreferences(prefName+"_pref",MODE_PRIVATE);
        //現在の時刻
        Calendar cl = Calendar.getInstance();
        //引数で指定した食品の賞味期限
        Calendar cl2 = Calendar.getInstance();
        cl2.set( pref2.getInt("year", cl.get(Calendar.YEAR) ), pref2.getInt("month", cl.get(Calendar.MONTH) ), pref2.getInt("day", cl.get(Calendar.DAY_OF_MONTH) ) );
        Date date1 = cl.getTime();
        Date date2 = cl2.getTime();

        long current_time = date1.getTime();
        long item_time = date2.getTime();

        long nokori = (item_time - current_time) / ( 1000 * 60 * 60 * 24 );

        return nokori;
    }
}