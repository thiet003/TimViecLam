## Ứng dụng tìm việc làm (TimViec)

Người thực hiện: **Vũ Đình Thiết**

### I. Ý tưởng

Với mong muốn giúp mọi người tìm được việc làm và đặc biệt là sinh viên đang tìm việc làm thực tập, nên em muốn tạo một ứng dụng để giúp sinh viên tìm được việc làm dễ dàng hơn và xây dựng được 1 CV cho riêng mình. Ứng dụng sẽ giúp người dùng tìm việc làm và kết nối đến trang web ứng tuyển chính thức của các nhà tuyển dụng.

 ***Mong muốn của em sẽ tạo một ứng dụng giúp tất cả mọi người đều có thể có việc làm để không bị thất nghiệp tuy nhiên do quy mô không cho phép nên chỉ làm được ứng dụng tìm việc làm thực tập cho sinh viên. Tương lai em sẽ cố gắng để hoàn thành ý tưởng lớn đó.***

### II. Các tính năng của ứng dụng

Ứng dụng sẽ có 2 tính năng chính:

1. Tìm kiếm việc làm theo các tiêu chí như địa điểm, lĩnh vực, kỹ năng, mức lương, vv. 
2. Tạo CV và cho phép người dùng sửa CV theo các thông tin và kĩ năng của riêng mình.

### III. Vai trò của các class

#### 1, Các activity

Ứng dụng có khá nhiều activity khác nhau và chủ yếu để hiện thị thông tin người dùng, thông tin việc làm cũng như chỉnh sửa thông tin cá nhân, thông tin người dùng, ...
#### 2, Các Fragment chính trong MainActivity

Có 4 Fragment chính trong MainActivity:

* **Home:** Hiển thị thông tin danh sách các việc làm và tìm kiếm việc làm.
* **MyJob:** trong Fragment này lại chia ra 2 Fragment khác là MyFavoriteJob và MyRecuimentJob lần lượt hiển thị các công việc người dùng yêu thích và người dùng đã ứng tuyển.
* **My CV:** Fragment này cho phép người dùng đi đến Activity tạo CV.
* **Person:** Fragment này giúp người dùng chỉnh sửa thông tin cá nhân của mình.

#### 3, Các Apdapter để hiện thị danh sách trong RecyclerView

Có khá nhiều danh sách cần hiển thị như danh sách việc làm, danh sách kĩ năng, học vấn, hoạt động để phục vụ cho việc tạo CV cho người dùng.

#### 4, Các class Model

Các class Model bao gồm:
* **User:** người dùng
* **Job:** Việc làm
* **Education:** Học vấn
* **Skill:** Kĩ năng
* **Experience:** Kinh nghiệm
* **JobFavorite:** Việc làm yêu thích
* **JobRecuiment:** Việc làm đã ứng tuyển

...

#### 5, Các class xử lí cơ sở dữ liệu

Vì cần lưu trữ cơ sở dữ liệu cho người dùng nên em có sử dụng RoomDatabase để xử lí dữ liệu cho người dùng.
Cụ thể là:
* ***JobDatabase:*** lưu việc làm

* ***JobFavoriteDatabase:*** lưu việc làm yêu thích

* ***JobRecuimentDatabase:*** lưu việc làm đã ứng tuyển

* ***UserDatabase:*** lưu thông tin người dùng

### IV. Các công nghệ sử dụng

**1. RecyclerView:** hiện thị danh sách động như danh sách việc làm, kĩ năng, kinh nghiệm, hoạt động,...
**2. RoomDataBase:** RoomDataBase giúp lưu và truy vấn cơ sở dữ liệu như Job, User,...
**3. Jsoup:** Jsoup dùng để lấy dữ liệu từ các trang web, ở ứng dụng này đã áp dụng Jsoup để lấy dữ liệu thông tin việc làm từ các trang tuyển dụng uy tín.
**4. PDFDocument:** dùng để lưu trữ CV dưới dạng PDF.

### V. Cách sử dụng ứng dụng

* Phần trang chủ để hiện thị việc làm, bấm vào từng việc làm sẽ hiện ra thông tin chi tiết của việc làm đó, nếu bấm vào ***ứng tuyển*** sẽ đi đến trang web ứng tuyển chính thức.
* Nếu bấm vào icon tym công việc đó sẽ được thêm vào danh sách việc làm yêu thích
* Bấm vào icon **Search** để tìm kiếm theo các tiêu chí như địa điểm, kĩ năng, lĩnh vực,...
* Chọn tab Tạo CV để chỉnh sửa thông tin cá nhân để tạo CV cho riêng mình.
* Chọn tab Hồ sơ để hiển thị và chỉnh sửa hồ sơ của người dùng.










