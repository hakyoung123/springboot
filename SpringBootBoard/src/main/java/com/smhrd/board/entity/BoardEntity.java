package com.smhrd.board.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BoardEntity {
	// entitiy에는 pk가 존재해야함.
	
	@Id // pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increase
	private Long id; // 인덱스번호
	
	// 각 컬럼들에거 not null 설정하기.(imgPath 빼고)
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String writer;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String content; // 내용의 경우 길이가 길어야하므로 
	
	private String imgPath;
	// img(file)을 넣는 것이 아니라 경로를 DB에 저장
	// DB서버에 직접적으로 이미지와 같은 파일을 저장 하지 않음. - 서버가 무거워짐, 데이터베이스를 불러오는데 시간이 걸림.
	// 이미지는 서버에 저장하고 해당 서버의 주소를 DB에 저장.
	
	@Column(nullable = false, updatable = false) // db에 저장시 insert는 가능하나 update는 불가능
	private LocalDate writeDay;
	
	//글 작성 시 자동으로 writeDay가 입력 되도록 코드 작성
	//entity가 생성 될 때 실행하는 코드
	@PrePersist
	protected void onCleate() {
		this.writeDay = LocalDate.now();
	}

}
