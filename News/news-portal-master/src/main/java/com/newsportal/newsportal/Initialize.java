package com.newsportal.newsportal;

import com.newsportal.newsportal.model.*;
import com.newsportal.newsportal.repository.*;
import com.newsportal.newsportal.source.Grp;
import com.newsportal.newsportal.source.PostGrp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Initialize implements CommandLineRunner {
    private UserRepository userRepository;
    private UserAccountRepository userAccountRepository;
    private SecurityQuestionRepository securityQuestionRepository;
    private GroupRepository groupRepository;
    private PostGroupRepository postGroupRepository;
    private PostRepository postRepository;
    private PermissionRepository permissionRepository;

    public Initialize(UserRepository userRepository, UserAccountRepository userAccountRepository, SecurityQuestionRepository securityQuestionRepository, GroupRepository groupRepository, PostGroupRepository postGroupRepository, PostRepository postRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
        this.securityQuestionRepository = securityQuestionRepository;
        this.groupRepository = groupRepository;
        this.postGroupRepository = postGroupRepository;
        this.postRepository = postRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(groupRepository.count() == 0)
            initGroup();
        if(postGroupRepository.count() == 0)
            initPostGroup();
        if(securityQuestionRepository.count() == 0)
            initSecurityQuestion();
        if(userRepository.count() == 0)
            initUser();
        if(postRepository.count() == 0)
            initPost();
        if(permissionRepository.count() == 0)
            initPermissions();

    }


    public void initGroup(){
        groupRepository.saveAll(
                Arrays.asList(
                        new Group().setId(1).setName("Müdür"),
                        new Group().setId(2).setName("Editör"),
                        new Group().setId(3).setName("Kullanıcı")
                )
        );
    }
    public void initPostGroup(){
        postGroupRepository.saveAll(
                Arrays.asList(
                        new PostGroup().setId(1).setName("Ekonomi").setDescription("Ekonomi Haberleri"),
                        new PostGroup().setId(2).setName("Spor").setDescription("Spor Haberleri"),
                        new PostGroup().setId(3).setName("Hava Durumu").setDescription("Hava Durumu Haberleri"),
                        new PostGroup().setId(4).setName("Siyaset").setDescription("Siyaset Haberleri")
                )
        );
    }
    public void initSecurityQuestion(){
        securityQuestionRepository.saveAll(
                Arrays.asList(
                        new SecurityQuestion().setId(1).setQuestion("İlkokul öğretmeninizin ismi nedir?"),
                        new SecurityQuestion().setId(2).setQuestion("Annenizin kızlık soyadı nedir?"),
                        new SecurityQuestion().setId(3).setQuestion("İlk evcil hayvanınızın ismi nedir?"),
                        new SecurityQuestion().setId(4).setQuestion("Doğduğunuz şehir neresidir?")
                )
        );
    }

    public void initUser(){
        userRepository.saveAll(
                Arrays.asList(
                        new User().setId(1).setName("admin").setLastname("admin").setAddress("Adminin adresi").setPhoneNumber("5316452452").setGroup(new Group().setId(Grp.ADMIN)).setBeInUse(true),
                        new User().setId(2).setName("editor").setLastname("editor").setAddress("Editörün Adresi").setPhoneNumber("5453556482").setGroup(new Group().setId(Grp.EDITOR)).setBeInUse(true)
                        .setPostGroup(Arrays.asList(new PostGroup().setId(PostGrp.ECONOMY), new PostGroup().setId(PostGrp.WEATHER), new PostGroup().setId(PostGrp.POLITICS), new PostGroup().setId(PostGrp.SPORT))),
                        new User().setId(3).setName("user").setLastname("user").setAddress("Kullanıcının Addresi").setPhoneNumber("5143125544").setGroup(new Group().setId(Grp.USER)).setBeInUse(true)
                )
        );
        userAccountRepository.saveAll(
                Arrays.asList(
                        new UserAccount()
                                .setUsername("admin").setPassword("admin")
                                .setSecurityQuestion(new SecurityQuestion().setId(1))
                                .setSecurityQuestionAnswer("admin")
                                .setUser(new User().setId(1)),
                        new UserAccount()
                                .setUsername("editor").setPassword("editor")
                                .setSecurityQuestion(new SecurityQuestion().setId(2))
                                .setSecurityQuestionAnswer("editor")
                                .setUser(new User().setId(2)),
                        new UserAccount()
                                .setUsername("user").setPassword("user")
                                .setSecurityQuestion(new SecurityQuestion().setId(4))
                                .setSecurityQuestionAnswer("user")
                                .setUser(new User().setId(3))

                )
        );
    }

    public void initPost(){
        postRepository.saveAll(
                Arrays.asList(
                        new Post().setAuthor(new User().setId(2)).setPostGroup(new PostGroup().setId(PostGrp.SPORT))
                        .setTitle("Ligler tamamlanamazsa Şampiyonlar Ligi'ne hangi takım gidecek? UEFA açıkladı!")
                        .setImageUrl("https://i4.hurimg.com/i/hurriyet/75/750x422/5ea1a2ab0f25440ce0a0a70d.jpg")
                        .setVerified(true).setPrivacy(false)
                        .setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Quis viverra nibh cras pulvinar mattis. Aliquet bibendum enim facilisis gravida. Etiam sit amet nisl purus in mollis. Orci eu lobortis elementum nibh tellus molestie. Volutpat est velit egestas dui id. Lacus sed viverra tellus in hac habitasse. In nisl nisi scelerisque eu ultrices vitae auctor eu augue. Quis vel eros donec ac odio tempor orci. Faucibus et molestie ac feugiat sed lectus vestibulum mattis. Sagittis nisl rhoncus mattis rhoncus urna neque viverra justo nec.\n" +
                                "\n" +
                                "Justo nec ultrices dui sapien eget mi. Et tortor at risus viverra adipiscing at in. Non curabitur gravida arcu ac tortor. Dignissim sodales ut eu sem integer vitae justo eget magna. In fermentum posuere urna nec tincidunt. Consectetur a erat nam at lectus urna duis. Donec pretium vulputate sapien nec sagittis aliquam malesuada bibendum. Sagittis purus sit amet volutpat consequat mauris. Ut aliquam purus sit amet luctus venenatis lectus. Sem et tortor consequat id porta nibh venenatis cras sed. Metus dictum at tempor commodo ullamcorper a lacus. Nam aliquam sem et tortor consequat id porta nibh venenatis. Dictum varius duis at consectetur lorem donec. Pretium nibh ipsum consequat nisl vel pretium.\n" +
                                "\n" +
                                "Diam ut venenatis tellus in metus vulputate. Id semper risus in hendrerit gravida rutrum quisque non tellus. Sit amet consectetur adipiscing elit ut aliquam. Pharetra vel turpis nunc eget lorem dolor sed. Mattis nunc sed blandit libero volutpat. Maecenas pharetra convallis posuere morbi leo urna molestie. Varius morbi enim nunc faucibus a. Semper risus in hendrerit gravida rutrum quisque. Nisl tincidunt eget nullam non nisi est sit amet. Id velit ut tortor pretium viverra suspendisse potenti nullam ac. Lobortis mattis aliquam faucibus purus in massa.\n" ),

                       new Post().setAuthor(new User().setId(2)).setPostGroup(new PostGroup().setId(PostGrp.WEATHER))
                        .setTitle("Meteoroloji'den sağanak ve kar uyarısı! Bölgeleri tek tek sıraladı ve...")
                        .setImageUrl("https://imgrosetta.mynet.com.tr/file/11948949/11948949-320x180.jpg")
                        .setVerified(true).setPrivacy(true)
                        .setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Quis viverra nibh cras pulvinar mattis. Aliquet bibendum enim facilisis gravida. Etiam sit amet nisl purus in mollis. Orci eu lobortis elementum nibh tellus molestie. Volutpat est velit egestas dui id. Lacus sed viverra tellus in hac habitasse. In nisl nisi scelerisque eu ultrices vitae auctor eu augue. Quis vel eros donec ac odio tempor orci. Faucibus et molestie ac feugiat sed lectus vestibulum mattis. Sagittis nisl rhoncus mattis rhoncus urna neque viverra justo nec.\n" +
                                "\n" +
                                "Justo nec ultrices dui sapien eget mi. Et tortor at risus viverra adipiscing at in. Non curabitur gravida arcu ac tortor. Dignissim sodales ut eu sem integer vitae justo eget magna. In fermentum posuere urna nec tincidunt. Consectetur a erat nam at lectus urna duis. Donec pretium vulputate sapien nec sagittis aliquam malesuada bibendum. Sagittis purus sit amet volutpat consequat mauris. Ut aliquam purus sit amet luctus venenatis lectus. Sem et tortor consequat id porta nibh venenatis cras sed. Metus dictum at tempor commodo ullamcorper a lacus. Nam aliquam sem et tortor consequat id porta nibh venenatis. Dictum varius duis at consectetur lorem donec. Pretium nibh ipsum consequat nisl vel pretium.\n" +
                                "\n" +
                                "Diam ut venenatis tellus in metus vulputate. Id semper risus in hendrerit gravida rutrum quisque non tellus. Sit amet consectetur adipiscing elit ut aliquam. Pharetra vel turpis nunc eget lorem dolor sed. Mattis nunc sed blandit libero volutpat. Maecenas pharetra convallis posuere morbi leo urna molestie. Varius morbi enim nunc faucibus a. Semper risus in hendrerit gravida rutrum quisque. Nisl tincidunt eget nullam non nisi est sit amet. Id velit ut tortor pretium viverra suspendisse potenti nullam ac. Lobortis mattis aliquam faucibus purus in massa.\n" ),
                        new Post().setAuthor(new User().setId(2)).setPostGroup(new PostGroup().setId(PostGrp.ECONOMY))
                        .setTitle("Petrol fiyatları 20 doların altına indi")
                        .setImageUrl("https://im.haberturk.com/2020/04/21/ver1587464320/2653356_810x458.jpg")
                        .setVerified(true).setPrivacy(false)
                        .setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Quis viverra nibh cras pulvinar mattis. Aliquet bibendum enim facilisis gravida. Etiam sit amet nisl purus in mollis. Orci eu lobortis elementum nibh tellus molestie. Volutpat est velit egestas dui id. Lacus sed viverra tellus in hac habitasse. In nisl nisi scelerisque eu ultrices vitae auctor eu augue. Quis vel eros donec ac odio tempor orci. Faucibus et molestie ac feugiat sed lectus vestibulum mattis. Sagittis nisl rhoncus mattis rhoncus urna neque viverra justo nec.\n" +
                                "\n" +
                                "Justo nec ultrices dui sapien eget mi. Et tortor at risus viverra adipiscing at in. Non curabitur gravida arcu ac tortor. Dignissim sodales ut eu sem integer vitae justo eget magna. In fermentum posuere urna nec tincidunt. Consectetur a erat nam at lectus urna duis. Donec pretium vulputate sapien nec sagittis aliquam malesuada bibendum. Sagittis purus sit amet volutpat consequat mauris. Ut aliquam purus sit amet luctus venenatis lectus. Sem et tortor consequat id porta nibh venenatis cras sed. Metus dictum at tempor commodo ullamcorper a lacus. Nam aliquam sem et tortor consequat id porta nibh venenatis. Dictum varius duis at consectetur lorem donec. Pretium nibh ipsum consequat nisl vel pretium.\n" +
                                "\n" +
                                "Diam ut venenatis tellus in metus vulputate. Id semper risus in hendrerit gravida rutrum quisque non tellus. Sit amet consectetur adipiscing elit ut aliquam. Pharetra vel turpis nunc eget lorem dolor sed. Mattis nunc sed blandit libero volutpat. Maecenas pharetra convallis posuere morbi leo urna molestie. Varius morbi enim nunc faucibus a. Semper risus in hendrerit gravida rutrum quisque. Nisl tincidunt eget nullam non nisi est sit amet. Id velit ut tortor pretium viverra suspendisse potenti nullam ac. Lobortis mattis aliquam faucibus purus in massa.\n" )
                )
        );
    }

    public void initPermissions(){
        permissionRepository.saveAll(
                Arrays.asList(
                    new Permission().setId(1).setName("Haber Oluşturma Yetkisi").setDescription("Mevcut haber türlerinden birine haber oluşturma yetkisi")
                    .setGroups(
                            Arrays.asList(
                                    new Group().setId(Grp.EDITOR)
                            )
                    ),
                    new Permission().setId(2).setName("Haber Güncelleme yada Silme Yetkisi").setDescription("Sahip olunan kategorideki tüm onaylanan yada onaylanmayan haberleri güncelleme yada silme yetkisi")
                    .setGroups(
                            Arrays.asList(
                                    new Group().setId(Grp.EDITOR)
                            )
                    ),
                    new Permission().setId(3).setName("Haber Onaylama Yetkisi").setDescription("Mevcut haberleri onaylama yada reddetme yetkisi")
                    .setGroups(
                            Arrays.asList(
                                    new Group().setId(Grp.ADMIN)
                            )
                    ),
                    new Permission().setId(4).setName("Kategori Ekleme, Silme ve Güncelleme Yetkisi").setDescription("Yeni kategori ekleme ve mevcut tüm kategorileri silme, güncelleme yetkisi")
                    .setGroups(
                            Arrays.asList(
                                    new Group().setId(Grp.ADMIN)
                            )
                    ),
                    new Permission().setId(5).setName("Editör Ekleme, Silme ve Güncelleme Yetkisi").setDescription("Yeni Editör ekleme ve mevcut tüm kategorileri silme, güncelleme yetkisi")
                    .setGroups(
                            Arrays.asList(
                                    new Group().setId(Grp.ADMIN)
                            )
                    )
                )
        );
    }

}
