package com.back.domain.wiseSaying.controller;

import com.back.AppTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {

  @BeforeEach
  void beforeEach() {
    AppTest.clear();
  }

  @Test
  @DisplayName("등록")
  void t1() {
    final String out = AppTest.run("""
        등록
        현재를 사랑하라
        작자미상
        등록
        세상은 험난해
        엉엉순대
        목록
        삭제?id=1
        삭제?id=1
        수정?id=2
        시져시져
        꿍꿍
        빌드
        종료
        """);

    assertThat(out).contains("명언 :").contains("작가 :").contains("1번 명언이 등록되었습니다.");
  }

  @Test
  @DisplayName("목록")
  void t2() {
    final String out = AppTest.run("""
        등록
        명언 1
        작자미상 1
        등록
        명언 2
        작자미상 2
        등록
        명언 3
        작자미상 3
        등록
        명언 4
        작자미상 4
        등록
        명언 5
        작자미상 5
        등록
        명언 6
        작자미상 6
        등록
        명언 7
        작자미상 7
        등록
        명언 8
        작자미상 8
        등록
        명언 9
        작자미상 9
        목록
        빌드
        종료
        """);

    assertThat(out).contains("9 / 작자미상 9 / 명언 9")
        .contains("8 / 작자미상 8 / 명언 8")
        .contains("7 / 작자미상 7 / 명언 7")
        .contains("6 / 작자미상 6 / 명언 6")
        .contains("5 / 작자미상 5 / 명언 5")
        .contains("페이지 : [1] / 2");
  }

  @Test
  @DisplayName("목록?page=2")
  void t3() {
    final String out = AppTest.run("""
        등록
        가나
        작자미상 1
        등록
        다라
        작자미상 2
        등록
        라마
        김나박이
        등록
        라마니
        이수
        등록
        라마산
        나얼
        등록
        라솔라
        박효신
        목록?page=2
        빌드
        종료
        """);

    assertThat(out).contains("1 / 작자미상 1 / 가나")
        .contains("페이지 : 1 / [2]");
  }

  @Test
  @DisplayName("목록?keywordType=author&keyword=이")
  void t4() {
    final String out = AppTest.run("""
        등록
        가나
        작자미상 1
        등록
        다라
        작자미상 2
        등록
        라마
        김나박이
        등록
        라마니
        이수
        등록
        라마산
        나얼
        등록
        라솔라
        박효신
        목록?keywordType=author&keyword=이
        빌드
        종료
        """);

    assertThat(out).contains("4 / 이수 / 라마니")
        .contains("3 / 김나박이 / 라마")
        .contains("페이지 : [1]");
  }

  @Test
  @DisplayName("목록?keywordType=content&keyword=라마&page=1")
  void t5() {
    final String out = AppTest.run("""
        등록
        가나
        작자미상 1
        등록
        다라
        작자미상 2
        등록
        라마
        김나박이
        등록
        라마니
        이수
        등록
        라마산
        나얼
        등록
        라솔라
        박효신
        목록?keywordType=content&keyword=라마&page=1
        빌드
        종료
        """);

    assertThat(out).contains("4 / 이수 / 라마니")
        .contains("3 / 김나박이 / 라마")
        .contains("페이지 : [1]");
  }
}