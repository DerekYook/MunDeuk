# MunDeuk

## Index
- [Overview](#overview)
- [Getting Started](#getting-started)
- [Documentation](#documentation)
- [Testing](#testing)
- [Authors](#authors)
- [License](#license)

## About Project
이 프로젝트는 기록용 웹서비스 API제작 프로젝트 입니다.

## Overview
- Object : 기록용 웹서비스 프로젝트
- Language : Java 17
- FrameWork : Sping Boot 3.2.2
- Databese : H2
- DevTool : IntelliJ, 


## Getting Started
### 사용자 요구사항
1. 사용자 등록
2. 사용자 삭제
&nbsp;&nbsp;- 사용자를 삭제하면 게시글도 모두 삭제해야 한다.
3. 게시글 작성
&nbsp;&nbsp;- 게시글은 게시판, 앨범, 지도정보 3가지 방식을 제공한다.
&nbsp;&nbsp;- 제한사항
    <p>(1) 게시판은 memo, todo-List 2가지 카테고리를 가진다.
    <p>(2) 앨범의 사진은 기본적으로 사진과 2줄의 코멘트를 작성하도록 한다.
    <p>(3) 게시글의 삭제, 숨김 기능을 제공한다.
    <p>(4) 지도에 위치정보를 기록할 수 있게한다.
    <p>(5) 활동에 따른 점수를 차등 제공한다. (하루 30점 한계 (예: memo 10개, 앨범 1, 지도 1, todo 3개 + 3개완))
    <p>- memo  : 1점 / 1개 (하루 10개 한계)
    <p>- todo-List : 1점 / 1개 , 3점 / 1개 완료
    <p>- 앨범 : 5점 / 1개
    <p>- 지도 : 3점 / 1개
4. 사용자 관리
&nbsp;&nbsp;- 사용자 본인이 삭제를 하면 휴면 처리로 한다.
&nbsp;&nbsp;- 관리자가 삭제시 사용자를 바로 삭제한다.
&nbsp;&nbsp;- 사용자가 휴면 혹은 삭제가 되면 게시글은 모두 숨김 처리한다.
    
## Documentation
<a href="https://drive.google.com/drive/folders/1ADAvIToiEl6eJv-lHRXVnPVXkr9qCVKR?usp=sharing" target="_blank">GoogleDrive</a>
    
## Testing
Postman과 Swagger를 이용한 테스트 진행 및 API 문서 작성

## Authors
- [DerekYook](https://github.com/DerekYook) - KyungDeuk Yook (wowykd@gmail.com)

## License
ⓒ 2024. DerekYook all rights reserved.
