## Jsoup trong Java


JSoup là một thư viện Java cho phép bạn phân tích cú pháp HTML và XML để trích xuất thông tin và thao tác với dữ liệu. Nó cung cấp một cách tiện lợi để truy cập các thành phần của tài liệu HTML, như các thẻ, thuộc tính và văn bản.

JSoup có thể được sử dụng để phân tích cú pháp trang web và trích xuất dữ liệu, cho phép bạn lấy dữ liệu từ các trang web khác nhau và sử dụng chúng trong ứng dụng của mình. JSoup cũng có thể được sử dụng để thay đổi cấu trúc của tài liệu HTML hoặc XML, cho phép bạn tạo ra các trang web hoặc tài liệu mới.

Các tính năng chính của JSoup bao gồm:

1. Phân tích cú pháp HTML và XML: JSoup cho phép bạn phân tích cú pháp HTML và XML để truy cập các thành phần của tài liệu.

2. Trích xuất dữ liệu: JSoup cung cấp các phương thức để trích xuất dữ liệu từ các thành phần của tài liệu, như các thẻ, thuộc tính và văn bản.

3. Thay đổi cấu trúc tài liệu: JSoup cung cấp các phương thức để thay đổi cấu trúc của tài liệu HTML hoặc XML, cho phép bạn tạo ra các trang web hoặc tài liệu mới.

4. Hỗ trợ các chức năng của jQuery: JSoup hỗ trợ các chức năng của jQuery, cho phép bạn thực hiện các thao tác với tài liệu HTML hoặc XML một cách dễ dàng.

JSoup rất dễ sử dụng và có thể được tích hợp vào ứng dụng của bạn với chỉ một vài dòng mã. Ví dụ, để truy cập một trang web và trích xuất tất cả các liên kết trong trang, bạn có thể sử dụng đoạn mã sau:
```c
Document doc = Jsoup.connect("http://example.com/").get();
Elements links = doc.select("a[href]");
for (Element link : links) {
    System.out.println(link.attr("href"));
}
```

Trong ví dụ này, JSoup được sử dụng để kết nối đến trang web "http://example.com/" và trích xuất tất cả các liên kết trong trang bằng cách sử dụng phương thức "select" và CSS selector "a[href]".

