# Java POST Proje

Bu bir **`MAVEN`** projesidir.

Bu proje **E-Fatura** sunucularında yeni kullanıcı açmak için **`Console`** üzerinden kullanılması amaçlanmıştır. 

Kullanımı ; 
> java -jar demo-0.0.1-SNAPSHOT.jar (vkn no) (testk,canlik)
 - _**canlik**_ : _Canlı Sunucuda Kullanıcı Açmak İçin_
 - _**testk**_ : _Test Sunucuda Kullanıcı Açmak İçin_
 - _**vkn**_ : _Vergi kimlik numarası_

Uygulama otomatik olarak Userlist 'in bulunduğu sunucudan argüman olarak girilen **VKN** numarası ile arama yapar ve kullanıcı verilerine erişim talebinde bulunur. Güvenlik doğrulaması yapıldıktan sonra gelen **JSON** verisini parçalar ve bize kayıt için gerekli olan şirkete ait _**mail:urn**_ ve _**Şirket Adını**_ cevap olarak döner ve veriyi tutar. Eğer Userlist böyle bir kullanıcıya sahip değil ise kullanıcıdan _**mail:urn**_ girilmesi istenerek kayıt oluşturur.

**Proje Kaynak Kodları İle Paylaşılmıştır**.

**Proje 25 MB Sınırını açtığı için bazı dosyalar aktarılamadı. Fakat kaynak kodlarına erişebilirsiniz.**
