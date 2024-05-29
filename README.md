# BUNT

SSAFY 1학기 최종 관통 프로젝트

## 🚩 목차

1. 기획 배경
2. 서비스 소개
3. 기능 소개
4. 기술 스택
5. 프로젝트 일정 및 기타 산출물

## :sparkles: 기획배경

### 개요

- 서비스 명칭: **번트**
- 서비스 내용: 높아지는 KBO의 인기와 함께 야구에 입문하는 야린이들을 위한 응원가, 뉴스, 게시판, 실시간 채팅, 미니게임을 제공하는 플랫폼

### 개발 인원
- 2명(박창호, 이수현)

### 개발 일정
- 5/16 ~ 5/23(8일)
- 5/16 ~ 5/18(설계)
- 5/19 ~ 5/20(백엔드 완성)
- 5/21 ~ 5/23(프론트엔드 완성)

### 목적

- **야구에 쉽게 입문할 수 있게 도와주는 역할**
- 구단별 응원가, 선수별 응원가를 쉽게 들려주고 팀 정보, 팀 뉴스, 팀 굿즈를 한 눈에 볼 수 있게 하여 야구에 대한 흥미도를 상승시키는 목적을 가진다.

### 현 상황

- 역대급 KBO 흥행
    - 오프라인<br/>
    ['벌써 200만 돌파' KBO 리그 인기 폭발, 역대급 페이스다→꿈의 천만 관중 시대 여나](https://www.starnewskorea.com/stview.php?no=2024042808181432891) <br/>
    ['200만 돌파' 프로야구 흥행몰이에 카페가 웃었다](https://view.asiae.co.kr/article/2024051211292150854) <br/>
    [한 경기만 빼고 전부 솔드아웃…한화, 올 시즌 19번째 매진](https://www.joongang.co.kr/article/25248638)
    - 온라인<br/>
    [티빙, 8개월 만에 쿠팡 제치며 토종 OTT 이용자 수 '1위'](https://www.newsis.com/view/?id=NISX20240503_0002723450&cID=13004&pID=13100)<br/>
    [티빙, 유료가입 50% 증가…최주희 'KBO 중계' 전략 통하나](https://www.shinailbo.co.kr/news/articleView.html?idxno=1864260) <br/>
---

## :sparkles: 서비스 소개
---
### 메인
---
#### 메인페이지
![mainpage](https://github.com/dev-prao/BUNT/assets/101629085/43ebba3a-03c9-4fdf-95ba-1e8260810edb)

#### 구단페이지(롯데)
![main_lotte](https://github.com/dev-prao/BUNT/assets/101629085/83364bcb-8f89-4409-97ec-374d29ff3653)

---
### 회원
---
#### 로그인
![login](https://github.com/dev-prao/BUNT/assets/101629085/27d00263-3c58-48c2-afb3-98bc36938e94)

#### 로그아웃
![logout](https://github.com/dev-prao/BUNT/assets/101629085/9d59331a-00ca-4de2-b5bd-d4db39680fd9)

#### 회원가입
![join](https://github.com/dev-prao/BUNT/assets/101629085/ea68741d-9964-44de-83c3-7064265b5009)

#### 마이페이지
![mypage](https://github.com/dev-prao/BUNT/assets/101629085/4f08b2b2-3890-4cbd-a7a5-4b50823cceb1)

#### 관리자페이지
![adminpage](https://github.com/dev-prao/BUNT/assets/101629085/ccd84ba5-0918-4b20-810b-86aaf8933d63)

---
### 뉴스
---
#### 뉴스목록 & 뉴스상세
![news_lotte](https://github.com/dev-prao/BUNT/assets/101629085/c428fddc-e1d7-4f2d-aa4b-6a235fd059a7)

---
### 게시판
---
#### 게시글 조회
![community_visitor](https://github.com/dev-prao/BUNT/assets/101629085/081b1c40-b4f7-41b9-99d5-72d9c86d965f)

#### 게시글 작성 / 수정 / 삭제 / 추천 / 댓글(수정,삭제)(회원)
![community_user](https://github.com/dev-prao/BUNT/assets/101629085/d4e6f3f8-553d-4d3d-8763-bfd509de3f23)

#### 게시글 검색
![search](https://github.com/dev-prao/BUNT/assets/101629085/c4fbe023-4ef0-4088-a120-f0df528f0393)

---
### 규칙(GEMINI API)
---
#### 키워드를 통한 답변 받기
![rules](https://github.com/dev-prao/BUNT/assets/101629085/6c8d06af-6afe-4551-bb09-c6aea8e9ebdf)

---
### 미니게임
---
#### 숫자야구
![game](https://github.com/dev-prao/BUNT/assets/101629085/7db89632-0efb-4189-a2ed-4085524eb03d)


### 실시간 채팅 API
![live_chat](https://github.com/dev-prao/BUNT/assets/101629085/113d78ac-0376-4a67-ab78-93c223b39a96)

---
## 기술 스택

### Frontend
- language: javascript
- framework: vue.js
- util: axios, pinia, cheerio, bootstrap

### Backend
- language: java
- framework: springboot
- util: MyBatis

### Database
- MySQL

### Communication
- Notion, Github

---
## 프로젝트 기타 산출물

### :sparkles: 요구사항 정의서
<img width="451" alt="image" src="https://github.com/dev-prao/BUNT/assets/101629085/c46448d9-ac0e-4508-8dd1-412d59161d65">

### :sparkles: API 명세서
<img width="402" alt="image" src="https://github.com/dev-prao/BUNT/assets/101629085/c2d50be9-1f62-4c00-924d-2a87269b2df1">

### :sparkles: 기능 명세서
<img width="514" alt="image" src="https://github.com/dev-prao/BUNT/assets/101629085/5f9ec6fd-01cd-4f4f-b9be-cfc2b75545b8">

### :sparkles: 유스케이스 다이어그램
<img width="594" alt="image" src="https://github.com/dev-prao/BUNT/assets/101629085/84e9405b-45d2-4d76-bab6-8a957a4dd838">

### :sparkles: 시퀀스 다이어그램
<img width="960" alt="image" src="https://github.com/dev-prao/BUNT/assets/101629085/4472d0cc-1334-4680-b07d-d517673184d4">

### :sparkles: 테이블 구조도
<img width="847" alt="image" src="https://github.com/dev-prao/BUNT/assets/101629085/c9d0fdd7-6e56-44df-94f1-c8f3284e60fc">

### :sparkles: 패키지 구조
```
├── README.md
├── backend_project
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.baseball.bunt
│   │   │   │       ├── BuntApplication.java
│   │   │   │       ├── config
│   │   │   │       │   ├── DBConfig.java
│   │   │   │       │   ├── SecurityConfig.java
│   │   │   │       │   └── SwaggerConfig.java
│   │   │   │       ├── controller
│   │   │   │       │   ├── AdminController.java
│   │   │   │       │   ├── CommunityCommentController.java
│   │   │   │       │   ├── CommunityController.java
│   │   │   │       │   ├── GameContoller.java
│   │   │   │       │   ├── PlayerController.java
│   │   │   │       │   └── UserController.java
│   │   │   │       ├── model
│   │   │   │       │   ├── dao
│   │   │   │       │   │   ├── BaseballGameDao.java
│   │   │   │       │   │   ├── CommunityCommentDao.java
│   │   │   │       │   │   ├── CommunityDao.java
│   │   │   │       │   │   ├── LikeListDao.java
│   │   │   │       │   │   ├── PlayerDao.java
│   │   │   │       │   │   └── UserDao.java
│   │   │   │       │   ├── dto
│   │   │   │       │   │   ├── common
│   │   │   │       │   │   │   └── User.java
│   │   │   │       │   │   ├── community
│   │   │   │       │   │   │   ├── CommunityBoard.java
│   │   │   │       │   │   │   ├── CommunityBoardComment.java
│   │   │   │       │   │   │   ├── Criteria.java
│   │   │   │       │   │   │   ├── LikeList.java
│   │   │   │       │   │   │   ├── Page.java
│   │   │   │       │   │   │   └── SearchCondition.java
│   │   │   │       │   │   ├── game
│   │   │   │       │   │   │   └── BaseballGame.java
│   │   │   │       │   │   └── team
│   │   │   │       │   │       ├── Player.java
│   │   │   │       │   │       └── Team.java
│   │   │   │       │   └── service
│   │   │   │           ├── BaseballGameService.java
│   │   │   │           ├── BaseballGameServiceImpl.java
│   │   │   │           ├── CommunityCommentService.java
│   │   │   │           ├── CommunityCommentServiceImpl.java
│   │   │   │           ├── CommunityService.java
│   │   │   │           ├── CommunityServiceImpl.java
│   │   │   │           ├── LikeListService.java
│   │   │   │           ├── LikeListServiceImpl.java
│   │   │   │           ├── PlayerService.java
│   │   │   │           ├── PlayerServiceImpl.java
│   │   │   │           ├── UserService.java
│   │   │   │           └── UserServiceImpl.java
│   │   │   └── resources
│   │   │       ├── mapper
│   │   │       │   ├── commentMapper.xml
│   │   │       │   ├── communityMapper.xml
│   │   │       │   ├── gameMapper.xml
│   │   │       │   ├── likeListMapper.xml
│   │   │       │   ├── playerMapper.xml
│   │   │       │   └── userMapper.xml
│   │   │       ├── sql
│   │   │       │   ├── bunt.sql
│   │   │       │   └── dummy.sql
│   └── test
│       └── java
│           └── com.baseball.bunt
│               ├── BuntApplicationTests.java
│               └── JwtTest.java

```
