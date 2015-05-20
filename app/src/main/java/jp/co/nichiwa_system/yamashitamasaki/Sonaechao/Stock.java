package jp.co.nichiwa_system.yamashitamasaki.Sonaechao;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.lang.reflect.GenericArrayType;

import jp.co.nichiwa_system.yamashitamasaki.Sonaechao.R;


public class Stock extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        //備蓄画像
        ImageView gas_iv = (ImageView)findViewById(R.id.gasView);
        ImageView match_iv = (ImageView)findViewById(R.id.matchView);
        ImageView bombe_iv = (ImageView)findViewById(R.id.bombeView);
        ImageView whistle_iv = (ImageView)findViewById(R.id.whistleView);
        ImageView shitagi_iv = (ImageView)findViewById(R.id.shitagiView);
        ImageView tissue_iv = (ImageView)findViewById(R.id.tissueView);
        ImageView almi_iv = (ImageView)findViewById(R.id.almiView);
        ImageView gunnte_iv = (ImageView)findViewById(R.id.gunnteView);

        ImageView mask_iv = (ImageView)findViewById(R.id.maskView);
        ImageView biniiru_iv = (ImageView)findViewById(R.id.biniiruView);
        ImageView kaityu_iv = (ImageView)findViewById(R.id.kaityuView);
        ImageView kankiri_iv = (ImageView)findViewById(R.id.kankiriView);
        ImageView radio_iv = (ImageView)findViewById(R.id.radioView);
        ImageView judenki_iv = (ImageView)findViewById(R.id.judenkiView);
        ImageView supun_iv = (ImageView)findViewById(R.id.supunView);
        ImageView koppu_iv = (ImageView)findViewById(R.id.koppuView);
        ImageView kyuugo_iv = (ImageView)findViewById(R.id.kyuugoView);
        ImageView nyuji_iv = (ImageView)findViewById(R.id.nyujiView);
        ImageView denti_iv = (ImageView)findViewById(R.id.dentiView);

        // 戻る画面
        Button Home = (Button)findViewById(R.id.home);//「ホーム」ボタン
        Button DispBtn = (Button)findViewById(R.id.settingbutton);//「設定」ボタン
        Button hijousyoku = (Button)findViewById(R.id.hijousyoku);//「非常食」ボタン

        Home.setOnClickListener( new OnClickListenerClass(".MainActivity",this) );
        hijousyoku.setOnClickListener( new OnClickListenerClass(".Hijousyoku",this ) );
        DispBtn.setOnClickListener( new OnClickListenerClass(".SubActivity",this ) );

        //ガスコロン＿ダイアログ
        gas_iv.setOnClickListener( new DialogOnClickListenerClass("ガスコンロ","gas_number",R.drawable.gas , false, this) );

        //マッチ＿ダイアログ
        match_iv.setOnClickListener( new DialogOnClickListenerClass( "マッチ・ライター","match_number",R.drawable.match, false, this) );

        //ボンベ
        bombe_iv.setOnClickListener( new DialogOnClickListenerClass( "ガスボンベ","bombe_number",R.drawable.bombe, true, this) );

        //ふえ
        whistle_iv.setOnClickListener( new DialogOnClickListenerClass( "笛","whistle_number",R.drawable.whistle, false, this) );

        //下着
        shitagi_iv.setOnClickListener( new DialogOnClickListenerClass( "下着","shitagi_number",R.drawable.shitagi, false, this) );

        //ティッシュ
        tissue_iv.setOnClickListener( new DialogOnClickListenerClass( "ティッシュ","tissue_number",R.drawable.tissue, false, this) );

        //アルミ
        almi_iv.setOnClickListener( new DialogOnClickListenerClass( "アルミホイル","almi_number",R.drawable.almi, false, this) );

        //軍手
        gunnte_iv.setOnClickListener( new DialogOnClickListenerClass( "軍手","gunnte_number",R.drawable.gunnte, false, this) );

        //マスク
        mask_iv.setOnClickListener( new DialogOnClickListenerClass( "マスク","mask_number",R.drawable.mask, false, this) );

        //ビニール袋
        biniiru_iv.setOnClickListener( new DialogOnClickListenerClass( "ビニール袋","mask_number",R.drawable.biniiru, false, this) );

        //懐中電灯
        kaityu_iv.setOnClickListener( new DialogOnClickListenerClass( "懐中電灯","kaityu_number",R.drawable.kaityu, false, this) );

        //缶切り
        kankiri_iv.setOnClickListener( new DialogOnClickListenerClass( "缶切り","kankiri_number",R.drawable.kankiri, false, this) );

        //ラジオ
        radio_iv.setOnClickListener( new DialogOnClickListenerClass( "ラジオ","radio_number",R.drawable.radio, false, this) );

        //充電器
        judenki_iv.setOnClickListener( new DialogOnClickListenerClass( "充電器","judenki_number",R.drawable.judenti, true, this) );

        //器・スプーン
        supun_iv.setOnClickListener( new DialogOnClickListenerClass( "器・スプーン","supun_number",R.drawable.supun, false, this) );

        //食器・コップ
        koppu_iv.setOnClickListener( new DialogOnClickListenerClass( "食器・コップ","koppu_number",R.drawable.koppu, false, this) );

        //救護セット
        kyuugo_iv.setOnClickListener( new DialogOnClickListenerClass( "救護セット","kyuugo_number",R.drawable.kyuugo, false, this) );

        //乳児セット
        nyuji_iv.setOnClickListener( new DialogOnClickListenerClass( "乳児セット","nyuji_number",R.drawable.nyuji, false, this) );

        //電池
        denti_iv.setOnClickListener( new DialogOnClickListenerClass( "電池","denti_number",R.drawable.denti, true, this) );

        //広告の設定
        AdView adview = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);
    }
}