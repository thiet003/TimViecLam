## Lưu dữ liệu trong cơ sở dữ liệu cục bộ bằng Room

Những ứng dụng xử lý lượng dữ liệu có cấu trúc với lượng không nhỏ có thể được hưởng nhiều lợi ích khi lưu dữ liệu đó trên máy. Trường hợp sử dụng phổ biến nhất là lưu các phần dữ liệu có liên quan vào bộ nhớ đệm. Bằng cách này, khi thiết bị không thể truy cập mạng, người dùng vẫn duyệt qua được nội dung đó.

Thư viện lưu trữ Room cung cấp một lớp trừu tượng qua SQLite để mang lại khả năng truy cập cơ sở dữ liệu dễ dàng, đồng thời khai thác toàn bộ sức mạnh của SQLite. Cụ thể, Room đem lại các lợi ích sau:

* Xác minh thời gian biên dịch của truy vấn SQL.
* Chú thích tiện lợi giúp giảm thiểu mã nguyên mẫu lặp lại, dễ mắc lỗi.
* Hợp lý hoá đường dẫn di chuyển cơ sở dữ liệu.

### Thiết lập

Để dùng Room trong ứng dụng, hãy thêm các phần phụ thuộc sau vào tệp build.gradle của ứng dụng:

```c
dependencies {
    def room_version = "2.5.0"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
}
```

### Thành phần chính

Có 3 thành phần chính trong Room:

* **Lớp cơ sở dữ liệu** lưu giữ cơ sở dữ liệu và đóng vai trò là điểm truy cập chính cho đường kết nối cơ bản đến dữ liệu cố định của ứng dụng.
* **Thực thể dữ liệu** biểu thị các bảng trong cơ sở dữ liệu của ứng dụng.
* **Đối tượng truy cập dữ liệu (DAO)** cung cấp các phương thức mà ứng dụng của bạn có thể dùng để truy vấn, cập nhật, chèn và xoá dữ liệu trong cơ sở dữ liệu.
Lớp cơ sở dữ liệu cung cấp cho ứng dụng của bạn các thực thể của DAO được liên kết với cơ sở dữ liệu đó. Đổi lại, ứng dụng có thể dùng DAO để truy xuất dữ liệu từ cơ sở dữ liệu dưới dạng thực thể của đối tượng thực thể dữ liệu được liên kết. Ứng dụng cũng có thể dùng các thực thể dữ liệu đã xác định để cập nhật các hàng trong bảng tương ứng hoặc tạo hàng mới để chèn dữ liệu. Hình 1 minh hoạ mối quan hệ giữa nhiều thành phần của Room.

![example](https://developer.android.com/static/images/training/data-storage/room_architecture.png?hl=vi)

### Phương thức triển khai mẫu

*Thực thể dữ liệu*

Mã sau đây xác định một thực thể dữ liệu User. Mỗi thực thể của User biểu thị một hàng trong bảng user thuộc cơ sở dữ liệu của ứng dụng.
```c
@Entity
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;
}
```

*Đối tượng truy cập dữ liệu (DAO)*

Mã sau đây xác định một `DAO` có tên `UserDao`. `UserDao` cung cấp các phương thức mà phần còn lại của ứng dụng sẽ dùng để tương tác với dữ liệu trong bảng `user`.

```c
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
           "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
```
### Cơ sở dữ liệu

Mã sau đây xác định một lớp `AppDatabase` để lưu giữ cơ sở dữ liệu. `AppDatabase` xác định cấu hình cơ sở dữ liệu và đóng vai trò là điểm truy cập chính của ứng dụng vào dữ liệu cố định. Lớp cơ sở dữ liệu phải đáp ứng các điều kiện sau:

* Lớp phải được chú thích bằng thẻ chú thích **@Database** có chứa mảng entities liệt kê mọi thực thể dữ liệu được liên kết với cơ sở dữ liệu.
Lớp này phải là một lớp trừu tượng mở rộng `RoomDatabase`.
* Đối với mỗi lớp `DAO` được liên kết với cơ sở dữ liệu, lớp cơ sở dữ liệu phải xác định một phương thức trừu tượng không có đối số và trả về một thực thể của lớp `DAO`.

```c
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
```

*Cách sử dụng*

Sau khi xác định thực thể dữ liệu, DAO và đối tượng cơ sở dữ liệu, bạn có thể dùng mã sau để tạo một thực thể của cơ sở dữ liệu:

```c
AppDatabase db = Room.databaseBuilder(getApplicationContext(),
        AppDatabase.class, "database-name").build();
```

Sau đó, bạn có thể dùng các phương thức trừu tượng từ AppDatabase để lấy thực thể của DAO. Đổi lại, bạn có thể dùng các phương thức từ thực thể DAO để tương tác với cơ sở dữ liệu:

```c
UserDao userDao = db.userDao();
List<User> users = userDao.getAll();
```



