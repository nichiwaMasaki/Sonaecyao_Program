package jp.co.nichiwa_system.yamashitamasaki.Sonaechao;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import jp.co.nichiwa_system.yamashitamasaki.Sonaechao.R;


public class Hijousyoku extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hijousyoku);

        // それぞれのボタンの情報を取得
        Button Home = (Button)findViewById(R.id.home);   //「ホーム」ボタン
        Button DispBtn = (Button)findViewById(R.id.settingbutton);    //「設定」ボタン
        Button Stock = (Button)findViewById(R.id.bichiku);     //「備蓄」ボタン

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

        // 場所を指定する
        Home.setOnClickListener(new OnClickListenerClass(".MainActivity", this));
        Stock.setOnClickListener(new OnClickListenerClass(".Stock", this));
        DispBtn.setOnClickListener(new OnClickListenerClass(".SubActivity", this));

        //ボタンアクションの処理
        retorutogohan_iv.setOnClickListener( new DialogOnClickListenerClass("レトルトごはん", "retorutogohan_number", R.drawable.retoruto_gohan, true,"袋", this ) );
        kandume_iv.setOnClickListener( new DialogOnClickListenerClass("缶詰（ごはん）", "kandume_number", R.drawable.kandume, true,"缶", this ) );
        kanmen_iv.setOnClickListener( new DialogOnClickListenerClass("乾麺", "kanmen_number", R.drawable.kanmen, true,"袋", this ) );
        kanpan_iv.setOnClickListener( new DialogOnClickListenerClass("カンパン", "kanpan_number", R.drawable.kanpan, true,"缶", this ) );
        kandume2_iv.setOnClickListener( new DialogOnClickListenerClass("缶詰（肉・魚）", "kandume2_number", R.drawable.kandume, true, "缶", this ) );
        retoruto_iv.setOnClickListener( new DialogOnClickListenerClass("レトルト食品", "retoruto_number", R.drawable.retoruto, true, "袋", this ) );
        furizu_dorai_iv.setOnClickListener( new DialogOnClickListenerClass("フリーズドライ", "furizu_dorai_number", R.drawable.furizu_dorai, true, "塊", this ) );
        mizu_iv.setOnClickListener( new DialogOnClickListenerClass("水", "mizu_number", R.drawable.mizu, true, "ℓ",this ) );
        karori_meito_iv.setOnClickListener( new DialogOnClickListenerClass("カロリーメイト", "karori_meito_number", R.drawable.karori_meito, true, "箱", this ) );
        okasi_iv.setOnClickListener( new DialogOnClickListenerClass("お菓子", "okasi_number", R.drawable.okasi, true, "箱・袋", this ) );
        rinyu_iv.setOnClickListener( new DialogOnClickListenerClass("離乳食", "rinyu_number", R.drawable.rinyu, true, this ) );
        konamilk_iv.setOnClickListener( new DialogOnClickListenerClass("粉ミルク", "konamilk_number", R.drawable.konamilk, true, this ) );

        //広告の設定
        AdView adview = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);
    }
}