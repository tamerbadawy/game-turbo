مشروع GAME TURBO للأندرويد - إصدار أولي. لا يعتمد على كون OPPO A6x نسخة 4G أو 5G.
المميزات: واجهة عربية، نسبة البطارية، قائمة التطبيقات القابلة للتشغيل، اختصار إعدادات عدم الإزعاج.
إنشاء APK: افتح المجلد في Android Studio ثم Build > Build Bundle(s) / APK(s) > Build APK(s). الملف الناتج عادة app/build/outputs/apk/debug/app-debug.apk.
ملاحظة: المشروع لا يكسر قيود أندرويد ولا يرفع تردد المعالج أو يضمن زيادة FPS.
بناء APK عبر GitHub Actions دون تثبيت Android Studio:
1) ارفع محتويات هذا المشروع إلى مستودع GitHub جديد.
2) افتح تبويب Actions.
3) اختر Build GAME TURBO APK ثم Run workflow.
4) بعد انتهاء البناء بنجاح، افتح نتيجة التشغيل ونزّل Artifact باسم GAME-TURBO-debug-APK.
5) فك ضغط الملف الناتج لتحصل على app-debug.apk.
