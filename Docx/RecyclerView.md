## Tạo danh sách động bằng RecyclerView   

RecyclerView giúp dễ dàng hiển thị hiệu quả các tập dữ liệu lớn. Bạn chỉ cần cung cấp dữ liệu và xác định giao diện của từng mục, thư viện RecyclerView sẽ linh động tạo các phần tử khi cần.

Đúng như tên gọi, RecyclerView tái chế các phần tử riêng lẻ đó. Khi một mục cuộn ra khỏi màn hình, RecyclerView sẽ không huỷ bỏ thành phần hiển thị của mục đó. Thay vào đó, RecyclerView sử dụng lại thành phần hiển thị này cho các mục mới đã cuộn trên màn hình. Việc tái sử dụng này cải thiện đáng kể hiệu suất, tăng khả năng phản hồi của ứng dụng và giảm mức tiêu thụ điện năng.

### Các lớp chính

Một số lớp sẽ phối hợp với nhau để tạo danh sách động.

* `RecyclerView` là `ViewGroup` chứa các thành phần hiển thị tương ứng với dữ liệu của bạn. Bản thân đây là một thành phần hiển thị, nên hãy thêm `RecyclerView` vào bố cục theo cách tương tự khi bạn thêm bất kỳ thành phần nào khác trên giao diện người dùng.

* Mỗi phần tử riêng lẻ trong danh sách được xác định bởi một đối tượng ngăn chứa thành phần hiển thị. Khi được tạo, ngăn chứa thành phần hiển thị không có bất kỳ dữ liệu liên kết nào. Sau khi ngăn chứa thành phần hiển thị được tạo, `RecyclerView` sẽ liên kết thành phần hiển thị với dữ liệu tương ứng. Bạn xác định ngăn chứa thành phần hiển thị bằng cách mở rộng `RecyclerView.ViewHolder`.

* RecyclerView yêu cầu các thành phần hiển thị đó và liên kết các thành phần hiển thị với dữ liệu tương ứng, bằng cách gọi các phương thức trong bộ chuyển đổi. Bạn xác định bộ chuyển đổi bằng cách mở rộng `RecyclerView.Adapter`.

* Trình quản lý bố cục sắp xếp các thành phần riêng lẻ trong danh sách của bạn. Bạn có thể sử dụng một trong các trình quản lý bố cục do thư viện RecyclerView cung cấp hoặc bạn có thể tự xác định. Tất cả trình quản lý bố cục đều dựa trên lớp trừu tượng `LayoutManager` của thư viện.

### Các bước triển khai RecyclerView

Nếu định sử dụng `RecyclerView` thì bạn sẽ có một vài việc cần làm. Những việc này sẽ được thảo luận chi tiết trong các phần sau.

* Trước hết, hãy quyết định giao diện của danh sách hoặc lưới. Thông thường, bạn sẽ có thể sử dụng một trong những trình quản lý bố cục chuẩn của thư viện `RecyclerView`.

* Thiết kế giao diện và hoạt động của từng phần tử trong danh sách. Dựa trên thiết kế này, hãy mở rộng lớp `ViewHolder`. Phiên bản `ViewHolder` của bạn cung cấp toàn bộ chức năng cho các mục trong danh sách. Ngăn chứa thành phần hiển thị của bạn là trình bao bọc xung quanh View và thành phần hiển thị đó do `RecyclerView` quản lý.

* Xác định `Adapter` liên kết dữ liệu của bạn với các thành phần hiển thị `ViewHolder`.

### Lên kế hoạch về bố cục

Các mục trong `RecyclerView` của bạn được một lớp `LayoutManager` sắp xếp. Thư viện RecyclerView cung cấp ba trình quản lý bố cục, giúp xử lý các trường hợp bố cục phổ biến nhất:

* `LinearLayoutManager` sắp xếp các mục theo danh sách một chiều.
* `GridLayoutManager` sắp xếp tất cả các mục theo dạng lưới hai chiều:
    * Nếu lưới được bố trí theo chiều dọc, `GridLayoutManager` sẽ cố gắng điều chỉnh sao cho mọi phần tử trong mỗi hàng có cùng độ rộng và chiều cao, nhưng chiều cao có thể tuỳ theo hàng.
    * Nếu lưới được bố trí theo chiều ngang, `GridLayoutManager` sẽ cố gắng điều chỉnh sao cho mọi phần tử trong mỗi cột có cùng độ rộng và chiều cao, nhưng độ rộng có thể tuỳ theo cột.
* `StaggeredGridLayoutManager` tương tự như `GridLayoutManager`, nhưng không yêu cầu các mục trong cùng một hàng có cùng chiều cao (đối với lưới dọc) hoặc các mục trong cùng một cột có cùng độ rộng (đối với lưới ngang). Do đó, các mục trong cùng một hàng hoặc cột có thể bù trừ cho nhau.

### Triển khai bộ chuyển đổi và ngăn chứa thành phần hiển thị

Sau khi đã xác định bố cục, bạn cần triển khai `Adapter` và `ViewHolder`. Hai lớp này phối hợp với nhau để xác định cách hiển thị dữ liệu của bạn. `ViewHolder` là trình bao bọc xung quanh một View chứa bố cục cho từng mục riêng lẻ trong danh sách. `Adapter` sẽ tạo các đối tượng `ViewHolder` nếu cần, đồng thời đặt dữ liệu cho các thành phần hiển thị đó. Quá trình gắn thành phần hiển thị với dữ liệu tương ứng được gọi là liên kết.

Khi xác định bộ chuyển đổi, bạn cần ghi đè ba phương thức chính:

* `onCreateViewHolder`(): RecyclerView gọi phương thức này bất cứ khi nào cần tạo `ViewHolder` mới. Phương thức này tạo và khởi động `ViewHolder` cùng với View đã liên kết, nhưng không điền vào nội dung của thành phần hiển thị – `ViewHolder` chưa liên kết với dữ liệu cụ thể.

* `onBindViewHolder`(): `RecyclerView` gọi phương thức này để liên kết `ViewHolder` với dữ liệu. Phương thức này tìm nạp dữ liệu thích hợp và sử dụng dữ liệu đó để điền vào bố cục của ngăn chứa thành phần hiển thị. Ví dụ: nếu `RecyclerView` hiển thị một danh sách tên, phương thức này có thể tìm tên thích hợp trong danh sách và điền vào tiện ích TextView của ngăn chứa thành phần hiển thị.

* `getItemCount`(): `RecyclerView` gọi phương thức này để lấy kích thước của tập dữ liệu. Ví dụ: trong một ứng dụng sổ địa chỉ, đây có thể là tổng số địa chỉ. `RecyclerView` sử dụng phương thức này để xác định thời điểm không thể hiển thị thêm mục nào.
