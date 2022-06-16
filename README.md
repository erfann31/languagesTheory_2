# languagesTheory_2
این پروژه شامل چندین بخش زیر است:
## Machine
![Untitled](https://user-images.githubusercontent.com/75057732/173968035-695e9228-3be3-413a-9a52-e0a2dc77bb5c.jpg)
## Grammar
گرامر بدست آمده از ماشین:<br />
A#0A|1B<br />
B#0A|1C<br />
C#DE<br />
D#KKKK<br />
E#0C|1F|qB<br />
F#0C|1G<br />
G#1H|qB<br />
H#1I|0H|qB<br />
I#1J<br />
J#dB<br />
K#1|0<br />
اگر میخواهید گرامر خطی راست به کد فاز یک بدهید تا عبارت منتظم تولید کند عبارت زیر را بدهید:<br />
A#0A<br />
A#1B<br />
B#0A<br />
B#1C<br />
C#0D<br />
C#1D<br />
D#1L<br />
D#0L<br />
L#1M<br />
L#0M<br />
M#0E<br />
M#1E<br />
E#0C<br />
E#1F<br />
E#qB<br />
F#0C<br />
F#1G<br />
G#1H<br />
G#qB<br />
H#1I<br />
H#0H<br />
H#qB<br />
I#1J<br />
J#dB<br />
K#1<br />
K#0<br />
## Regular-expression
عبارت منتظم بدست آمده از گرامر:<br />
دانلود فایل متنی عبارت منظم: 
[RE.txt](https://github.com/erfann31/languagesTheory_2/files/8914625/RE.txt)
## User Interface
این بخش هم از روی شکل ماشین انجام شد و منو های خرید و انتخاب محصول پیاده سازی شد؛ به این شکل که فایلی حاوی محتویات ماشین به برنامه داده میشود و سیستم بعد از ساختن لیست محصولات منوی خرید را نمایش میدهد. پس از فشردن دکمه خرید و انتخاب محصول و تایید، موجودی چک میشود و وارد عملیات پرداخت میشود؛ اگر پرداخت با موفقیت انجام شد، تک محصول تحویل داده میشود. مدل دستگاه را در پایین میبینید.
![image](https://user-images.githubusercontent.com/75057732/173906440-dfb70403-a3e0-4fd0-9a0a-50329aa98289.png)
![image](https://user-images.githubusercontent.com/75057732/173906562-c341ffe5-bc64-4ee1-bc8d-7e11e6bb460c.png)
## Generate machine code
کد دستگاه طبق شکل ماشین که قبلا دیدیم تولید میشود.
## Code for derivation of machine code from regular expression
برای این بخش ما یک فایل شامل تاریخچه ماشین را خوانده و بعد از هر بار خاموش شدن دستگاه، تاریخچه را بروز رسانی میکند که این کار توسط فیلتری اجرا میشود که با مدل کردن dfa ماشین انجام شده است.
