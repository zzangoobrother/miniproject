# 20조 REWIND Backend

### Team 
+ Frontend : 오세명,한우석 (REACT)
+ Backend :  오준석,최선강 (SPRING)

### REWIND란?
+ 그 등안 Backend,Frontend 가 공부하고 정리했던 내용들을 각 프레임워크별 (Spring,React,Node.js)로 모두 공유할 수 있는 게시판을 구현하려고 합니다. 게시판 은 기본적으로 메인페이지에 모든 프레임워크들의 게시글들이 한번에 보여지며 각 카테고리속에는 그에 해당하는 게시글들만 보여지도록 구성할 계획입니다.

#### Objective
1.  Frontend와 Backend 다른환경에서의 연동(CORS)
2.  회원가입 & Spring에서 JWT 방식의 로그인
3.  게시판 구현(CRUD 적용,프론트에서 작성시 마크다운형태로 구현)
4.  댓글 작성(CRUD 적용)
5.  메인페이지 페이징,카테고리별 페이징

### Project Collaboration Process
<details markdown = "1">
<summary>
API설계
</summary>
 <div style="width:60%; margin: auto" >
<img src = "https://media.vlpt.us/images/junseokoo/post/cecb061f-ef1f-41d7-a2d5-2d467c8d8012/%EC%84%A4%EA%B3%84%201.PNG">
<img src = "https://media.vlpt.us/images/junseokoo/post/3532dbc5-1f09-46e5-ae68-faf89f669f73/%EC%84%A4%EA%B3%842.PNG">
<img src = "https://media.vlpt.us/images/junseokoo/post/a9e40ba8-9150-48c5-8096-5d1db26eadfe/%EC%84%A4%EA%B3%843.PNG">
<img src = "https://media.vlpt.us/images/junseokoo/post/364e0c2d-c5a1-4300-93b0-bcd28e80437e/%EC%84%A4%EA%B3%844.PNG">
<img src = "https://media.vlpt.us/images/junseokoo/post/651313d2-3303-4220-aae6-ae84b7fd6fed/%EC%84%A4%EA%B3%845.PNG">
<img src = "https://media.vlpt.us/images/junseokoo/post/ecfbc5db-0e59-426c-9536-1f2280fc0377/%EC%84%A4%EA%B3%846.PNG">
 </div></details>

<details markdown = "1">
<summary>
Diagrams
</summary>
 <div style="margin: 0px 0px 0px 300px" >
<img src= "https://media.vlpt.us/images/junseokoo/post/b2852b8c-d5b8-46e9-ad48-7809a33ee04e/%EC%BA%A1%EC%B2%98.PNG">
 </div></details>


<details markdown = "1">
<summary>
문제점 / 해결과정
</summary>


## ERROR_CONNECTION_REFUSED

+ 프론트쪽 콘솔에서 GET요청을 많이 했을 시 위 제목가 같은 에러가 불규칙적으로 발생했습니다. 정확한 원인은 알 수 없었으며,시도해본방법으로는 먼저 서버를 재실행 하는 방법으로 위 문제를 해결하였으나 일시적일 뿐 통신을 진행하다보면 또다시 발생하는 경우가 종종 발생하였습니다. 그래서 또다른 방법으로는 GitBash에서 아예 서버를 계속 켜놓으면 어떨까 라는 생각에
  nohup java -jarspring-0.0.1-SNAPSHOT.jar &  를 이용해 서버를 계속 켜두었을때는 위와 같은 문제가 발생은 아직까지는 하지 않았지만 이 방법은 프론트쪽에서 뭘하는지 로그를 전혀 확인할 수 없었으며 서버를 재배포 할때 계속 서버를 kill해주는 번거로움이 있었습니다. 현재까지도 정확한 원인과 해결방법은 찾지 못하였으며 앞으로도 위의 에러가 발생시 쉽게 대처할 수 있도록 정확한 원인과 해결 방법을 숙지해야할 필요가 있어보입니다.


## 카카오 로그인
<div style="width: 60%; margin: 20px auto" >
<img src = "https://media.vlpt.us/images/junseokoo/post/2e4ee263-f81b-4144-9d54-4c58c6a2b57b/qwert.PNG">
</div>

+ https://kauth.kakao.com/oauth/authorize?client_id={REST_API_KEY}&redirect_uri={REDIRECT_URI}&response_type=code
  프로트 서버에서 위 링크를 실행합니다. https://kauth.kakao.com/oauth/authorize 서버로 통신하여 client_id의 REST_API_KEY를
  확인 그리고 카카오계정을 확인하여 카카오서버에서 redirect_uri의 url로 통신합니다. 그리고 서버에서는 카카오 서버에서 보내온
  정보를 가지고 기존 회원이면 카카오id만 db에 저장하여 강제 로그인하고, 기존 회원이 아니면 회원가입과 강제 로그인을 합니다.여기서 Frontend와 Backend의 통신과정에서 카카오 로그인을 시도하면 계속 500에러가 떴었는데 구글도 찾아보고 했지만 도무지 방법을 알 수 없었을때
REDIRECT_URI이 기존 설계 경로와는 다르게 설정되어있었다는 것을 발견했었습니다. 작은 실수지만 이렇게 많은 시간을 잡아먹을 수 있다는것을 다시한번 느끼게 되었으며 앞으로는 수정,변경,작성시에 최대한 꼼꼼하게 살펴봐야겠다 라는 생각이 다시금 들게 되었습니다.







## JWT 구현

+ 처음 프로젝트에서 시도 했던 구현이 회원가입과 로그인 구현입니다. 로그인한 회원이
  정상적인 회원이라면 토큰을 발행하여 프로트 서버에 보냅니다. 그리고 프론트에서는
  http 헤더에 토큰을 저장하여 매번 통신을 할때 마다 서버는 토큰을 확인합니다.
<div style="width: 60%; margin: 20px auto" >
<img src= "https://media.vlpt.us/images/junseokoo/post/8c49c470-c19c-47cb-9106-d7a219bcd33c/asdf.PNG">
</div><div style="width: 60%; margin: 20px auto" >
<img src = "https://media.vlpt.us/images/junseokoo/post/639091d3-3006-4fcb-a88f-f95ebdb79d5e/asdfgh.PNG">
</div>

+ 토큰을 완성 후 프론트에서 새로고침 후에 로그인이 풀리는 현상이 나타났습니다.
  원인은 서버에서 토큰을 만들고 통신하는 과정에서 하나의 토큰이 아닌
  accessToken과 refreshToken을 동시에 생성하여 프론트 서버에서 유효시간이 있는 토큰의
  삭제 여부에 따라 두 토큰을 비교하여 새로고침 후 로그인이 풀리거나 유지되게 해야 했습니다.
  서버에서 보낸토큰은 accessToken만 보내서 이런 현상이 발생하였습니다.
  이 현상을 발견한 시점이 프로젝트 종료 하루 전이라 refreshToken 생성은 못했지만,
  다음 프로젝트에서는 refreshToken 생성을 구현하여 이 같은 현상이 발생 안하도록 구현 하도록
  하겠습니다.

## TeamWork
+ 이번에 처음으로 Frontend와 Backend가 협력하여 프로젝트를 진행해보았는데 저희 조 같은경우는 우선적으로 `API설계`에 많은 시간을 투자했습니다. 이 과정 덕분에 오류 발생,수정,삭제부분이 생길시 다른조에  비해서 좀더 원활하게 진행이 되었다고 생각합니다. API설계의 중요성을 다시금 깨달았으며 매우 중요한 과정이란것을 알게 되었습니다.
그리고 Frontend와 Backend가 서로의 요구사항 및 변경사항을 당연히 모두가 100% 만족은 할 수 없었겠지만, `지속적인 소통`을 통해 서로의 만족을 채우려고 노력했던 Team이었다고 생각합니다.Frontend와 Backend의 `배려`가 너무 돋보이는 Team이었고 좋은 분위기 속에서 프로젝트를 진행하였기 때문에 목표 한 결과가 나왔다고 생각합니다.


</details>

[YOUTUBE 영상](http://youtube.com) 