# FoodieNest

FoodieNest adalah aplikasi Android yang memungkinkan pengguna menjelajahi berbagai restoran, mencari restoran berdasarkan nama, serta menyimpan restoran favorit secara lokal. Aplikasi ini menggunakan API dari [Restaurant API Dicoding](https://restaurant-api.dicoding.dev/) untuk menyediakan berbagai pilihan restoran. Selain itu, FoodieNest mendukung pengaturan tema gelap/terang yang dapat disimpan secara lokal.

## Fitur
- Pencarian restoran berdasarkan nama.
- Daftar restoran langsung ditampilkan di halaman utama.
- Favorit: Simpan restoran yang disukai untuk diakses kapan saja secara offline.
- Settings: Yang berisi pilihan untuk ganti tema seperti dark dan light mode, Button untuk reset semua data yang telah difavoritkan oleh pengguna, dan juga ada button untuk menampilkan tentang aplikasi seperti versi dan deskripsi singkatnya.
- Tema Terang/Gelap: Tema aplikasi dapat disimpan dan diterapkan secara lokal.
- Offline Support untuk daftar favorit dengan SQLite.

## Penggunaan
1.	Luncurkan aplikasi FoodieNest di perangkat Android Anda.
2.	Di halaman utama (Home), jelajahi daftar restoran atau gunakan fitur Pencarian untuk menemukan restoran yang diinginkan.
3.	Klik salah satu restoran untuk melihat detail lebih lengkap (fitur ini dapat dikembangkan lebih lanjut).
4.	Tekan ikon Favorit (jika ada di detail restoran) untuk menyimpan restoran favorit Anda.
5.	Akses tab Favorites untuk melihat daftar restoran yang telah Anda simpan.
6.	Gunakan menu Settings untuk mengubah tema aplikasi (Dark Mode / Light Mode) serta mengatur ulang data favorit.

## Implementasi Teknis

FoodieNest dikembangkan sebagai aplikasi Android berbasis Java yang memanfaatkan API dari Dicoding Restaurant API untuk menyediakan data berbagai restoran. Navigasi dalam aplikasi menggunakan pendekatan berbasis fragment dengan dua bagian utama, yaitu Home dan Favorites, yang dikendalikan melalui BottomNavigationView. Pada bagian Home, pengguna dapat melihat daftar restoran yang ditampilkan menggunakan RecyclerView, serta dapat mencari restoran berdasarkan nama melalui fitur pencarian yang terintegrasi langsung dengan API.

Pengambilan data dari API dilakukan menggunakan Retrofit, sebuah pustaka HTTP yang efisien dan fleksibel untuk konsumsi RESTful API. Data yang diterima dari API diproses untuk menampilkan informasi seperti nama restoran, gambar, dan rating. Untuk mendukung pengalaman penggunaan secara offline, FoodieNest menyediakan fitur Favorit yang memungkinkan pengguna menyimpan restoran ke dalam database lokal menggunakan SQLite. Seluruh proses penyimpanan dan pengelolaan data favorit dilakukan melalui kelas DatabaseHelper, yang menyediakan berbagai metode untuk menambah, mengambil, dan menghapus data favorit.

Selain itu, aplikasi ini mendukung pengaturan Tema Gelap/Terang yang dapat diubah langsung oleh pengguna melalui halaman Settings. Pilihan tema tersebut disimpan secara lokal menggunakan SharedPreferences, sehingga tetap diterapkan secara konsisten meskipun aplikasi ditutup atau dijalankan kembali. Dengan pendekatan ini, FoodieNest berusaha memberikan pengalaman pengguna yang personal dan nyaman baik saat online maupun offline.