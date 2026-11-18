package com.gameturbo.app
import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.content.pm.PackageManager
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.*
class MainActivity: Activity() {
 private val bg=Color.rgb(7,12,24); private val panel=Color.rgb(16,26,45); private val cyan=Color.rgb(0,217,255)
 private fun dp(v:Int)=(v*resources.displayMetrics.density).toInt()
 private fun label(s:String,n:Float,c:Int,b:Boolean=false)=TextView(this).apply { text=s;textSize=n;setTextColor(c);gravity=Gravity.CENTER;if(b)setTypeface(null,Typeface.BOLD) }
 override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);window.statusBarColor=bg;window.navigationBarColor=bg;home()}
 private fun home(){
  val root=LinearLayout(this).apply{orientation=1;setPadding(dp(20),dp(24),dp(20),dp(18));setBackgroundColor(bg)}
  root.addView(label("GAME TURBO",28f,Color.WHITE,true));root.addView(label("BOOST YOUR GAME EXPERIENCE",11f,cyan,true))
  val hero=LinearLayout(this).apply{orientation=1;gravity=Gravity.CENTER;setPadding(dp(16),dp(24),dp(16),dp(24));setBackgroundColor(panel)}
  hero.addView(label("◈",58f,cyan,true));hero.addView(label("وضع الألعاب",24f,Color.WHITE,true));hero.addView(label("جاهز لتجربة لعب أكثر تركيزًا",14f,Color.LTGRAY))
  val bm=getSystemService(Context.BATTERY_SERVICE) as android.os.BatteryManager
  val level=bm.getIntProperty(android.os.BatteryManager.BATTERY_PROPERTY_CAPACITY)
  hero.addView(label("البطارية: "+if(level>=0)"$level%" else "غير متاحة",15f,cyan,true))
  hero.addView(Button(this).apply{text="عرض الألعاب المثبتة";setOnClickListener{games()}},LinearLayout.LayoutParams(-1,dp(52)).apply{topMargin=dp(18)})
  root.addView(hero,LinearLayout.LayoutParams(-1,0,1f).apply{topMargin=dp(24)})
  root.addView(Button(this).apply{text="فتح إعدادات عدم الإزعاج";setOnClickListener{try{startActivity(Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS"))}catch(_:Exception){}}})
  root.addView(label("لا يرفع التطبيق تردد المعالج ولا يضمن زيادة FPS؛ هذه الإمكانيات يتحكم بها النظام والشركة المصنّعة.",11f,Color.LTGRAY))
  setContentView(root)
 }
 private fun games(){
  val root=LinearLayout(this).apply{orientation=1;setPadding(dp(16),dp(20),dp(16),dp(16));setBackgroundColor(bg)}
  root.addView(label("الألعاب والتطبيقات",23f,Color.WHITE,true))
  val scroll=ScrollView(this);val list=LinearLayout(this).apply{orientation=1}
  val apps=packageManager.getInstalledApplications(PackageManager.GET_META_DATA).mapNotNull{a->packageManager.getLaunchIntentForPackage(a.packageName)?.let{Triple(a,it,packageManager.getApplicationLabel(a).toString())}}.filter{it.first.packageName!=packageName}.sortedBy{it.third.lowercase()}
  apps.forEach{(_,intent,name)->list.addView(Button(this).apply{text="▶  $name";gravity=Gravity.START or Gravity.CENTER_VERTICAL;setOnClickListener{startActivity(intent)}})}
  if(apps.isEmpty())list.addView(label("لا توجد تطبيقات قابلة للتشغيل.",14f,Color.LTGRAY))
  scroll.addView(list);root.addView(scroll,LinearLayout.LayoutParams(-1,0,1f));root.addView(Button(this).apply{text="رجوع";setOnClickListener{home()}});setContentView(root)
 }
}
