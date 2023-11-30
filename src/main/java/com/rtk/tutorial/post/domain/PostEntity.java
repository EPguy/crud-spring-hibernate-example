package com.rtk.tutorial.post.domain;

import com.rtk.tutorial.common.utils.SHA256;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity(name="post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleteFlag;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public void encryptPassword() throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();
        this.password = sha256.encrypt(password);
    }
}
