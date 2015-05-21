package jp.co.nichiwa_system.yamashitamasaki.Sonaechao;

import android.app.Activity;

/**
 * Created by yamashita.masaki on 2015/05/15.
 */
public class ItemClass {
    String ItemName;            //アイテムの名前
    String prefName;            //プレファレンス名
    int Drawable_Location;   //画像の場所
    String Unit;                //単位数
    boolean Calendar_flag;   //フラグ
    Activity act;               //アクティビティ
    int Drawable_Icon;        //アイコン

    public ItemClass() {}

    public  ItemClass(String ItemName, String prefName, int Drawable_Location, boolean Calendar_flag,Activity act ) {
        this.ItemName = ItemName;
        this.prefName = prefName;
        this.Drawable_Location = Drawable_Location;
        this.Calendar_flag = Calendar_flag;
        this.act = act;
    }

    public ItemClass(String ItemName, String prefName, int Drawable_Location, boolean Calendar_flag, String Unit, Activity act ) {
        this.ItemName = ItemName;
        this.prefName = prefName;
        this.Drawable_Location = Drawable_Location;
        this.Unit = Unit;
        this.Calendar_flag = Calendar_flag;
        this.act = act;
    }

    public void setIcon( int Drawable_Icon ) {
        this.Drawable_Icon = Drawable_Icon;
    }

    public int getIcon() {
        return Drawable_Icon;
    }
}