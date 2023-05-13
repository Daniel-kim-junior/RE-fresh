# T-KO (Techin-Kosa)
![refresh](https://github.com/TKO-RE-fresh/RE-fresh/assets/67178562/34615cd0-9788-4351-9769-21ef9feef135)

## 목차
- [Refresh](#T-KO)
  - [목차](#목차)
  - [서비스 소개](#서비스-소개)
    - [📋 기술 스택](#-기술-스택)
  - [프로젝트 파일 구조](#프로젝트-파일-구조)
  - [Git](#Git-브랜치-전략)
  - [산출물](#산출물)

 <br><br>
 
 ## 서비스 소개
 1. 개발 기간 : 2023.05.01 ~ 2022.05.09 (총 9일)

 2. 인원 (총 4인)
	 - 김민성 : 팀장, 달력 FrontEnd, 달력 BackEnd, 로그인 BackEnd, 환경 설정
	 - 김대엽 : 연차 신청 FrontEnd, 연차 신청 BackEnd, 공통 컴포넌트 UI(SideBar, Header)
	 - 홍승희 : ADMIN(사원 생성, 사원 조회, 페이징)
	 - 박주희 : ADMIN(연차 조회, 연차 신청 내역 조회, DB 테이블 설계)
	 
<br><br>

### 📋 기술 스택
1. 이슈관리 : ![Jira](https://img.shields.io/badge/jira-%230A0FFF.svg?style=for-the-badge&logo=jira&logoColor=white)

2. 형상관리 : ![GitHub](https://img.shields.io/badge/github-%23000000.svg?style=for-the-badge&logo=github&logoColor=white)

3. 커뮤니케이션 : ![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)

4. 개발 환경#06B6D4
	- OS : ![Windows](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)10
	- IDE
	  -  <img src="https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white" alt="IntelliJ IDEA" style="zoom:80%;" />
	  -  <img src="https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white" alt="Visual Studio Code" style="zoom:80%;" />
	- Database : ![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)RDS (mysql)
		
5. 상세 사용
	- Backend
		-  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" alt="Java" style="zoom:80%;" /> (Open JDK 11)
		-  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring" style="zoom: 80%;" /> (Spring Boot 2.7.11)
		-  ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)7.6.1
	- Frontend
		-   ![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)<img src="https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E" alt="JavaScript" style="zoom:80%;" />(ES6)
		-   ![TailwindCSS](https://img.shields.io/badge/tailwindcss-%230A0FFF.svg?style=for-the-badge&logo=tailwindcss&logoColor=white)
		-   ![Thymeleaf](https://img.shields.io/badge/thymeleaf-%236DB33F.svg?style=for-the-badge&logo=thymeleaf&logoColor=white)

<br><br>

## 프로젝트 파일 구조
- FrontEnd(Thymeleaf)
```
📦src
 ┣ 📂api
 ┃ ┗ 📜calendarApi.js
 ┣ 📂assets
 ┃ ┗ 📂images
 ┃ ┃ ┣ 📜logo.svg
 ┃ ┃ ┣ 📜refresh_logo.PNG
 ┃ ┃ ┗ 📜user.svg
 ┣ 📂auth
 ┃ ┗ 📜auth.html
 ┣ 📂error
 ┃ ┗ 📜errorPage.html
 ┣ 📂fragments
 ┃ ┣ 📂header
 ┃ ┃ ┗ 📜header.html
 ┃ ┣ 📂sidebar
 ┃ ┃ ┗ 📜sidebar.html
 ┃ ┗ 📜header.html
 ┣ 📂pages
 ┃ ┣ 📂admin
 ┃ ┃ ┣ 📂annual
 ┃ ┃ ┃ ┣ 📜annualmanage.html
 ┃ ┃ ┃ ┣ 📜annualmanage.js
 ┃ ┃ ┃ ┗ 📜annualmodal.html
 ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┣ 📜adminTab.html
 ┃ ┃ ┃ ┣ 📜adminTab.js
 ┃ ┃ ┃ ┣ 📜createMemberForm.html
 ┃ ┃ ┃ ┗ 📜memberList.html
 ┃ ┃ ┣ 📜annualmanage.html
 ┃ ┃ ┣ 📜annualmanage.js
 ┃ ┃ ┗ 📜annualmodal.html
 ┃ ┣ 📂calendar
 ┃ ┃ ┣ 📂aside
 ┃ ┃ ┃ ┣ 📂asideDepartment
 ┃ ┃ ┃ ┃ ┣ 📜app.js
 ┃ ┃ ┃ ┃ ┣ 📜asideDepartment.js
 ┃ ┃ ┃ ┃ ┣ 📜hook.js
 ┃ ┃ ┃ ┃ ┗ 📜main.js
 ┃ ┃ ┃ ┣ 📂asideHeader
 ┃ ┃ ┃ ┃ ┣ 📜app.js
 ┃ ┃ ┃ ┃ ┣ 📜asideHeader.js
 ┃ ┃ ┃ ┃ ┣ 📜hook.js
 ┃ ┃ ┃ ┃ ┗ 📜main.js
 ┃ ┃ ┃ ┗ 📂asideMember
 ┃ ┃ ┃ ┃ ┣ 📜app.js
 ┃ ┃ ┃ ┃ ┣ 📜asideMember.js
 ┃ ┃ ┃ ┃ ┣ 📜hook.js
 ┃ ┃ ┃ ┃ ┗ 📜main.js
 ┃ ┃ ┣ 📂header
 ┃ ┃ ┃ ┣ 📂departmentOption
 ┃ ┃ ┃ ┃ ┣ 📜app.js
 ┃ ┃ ┃ ┃ ┣ 📜departOptionList.js
 ┃ ┃ ┃ ┃ ┣ 📜hook.js
 ┃ ┃ ┃ ┃ ┗ 📜main.js
 ┃ ┃ ┃ ┣ 📜app.js
 ┃ ┃ ┃ ┣ 📜hook.js
 ┃ ┃ ┃ ┣ 📜main.js
 ┃ ┃ ┃ ┗ 📜optionList.js
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📜app.js
 ┃ ┃ ┃ ┣ 📜calendar.js
 ┃ ┃ ┃ ┣ 📜hook.js
 ┃ ┃ ┃ ┗ 📜main.js
 ┃ ┃ ┗ 📜calendar.html
 ┃ ┣ 📂leaveRequest
 ┃ ┃ ┗ 📜leaveRequest.html
 ┃ ┗ 📂mypage
 ┃ ┃ ┗ 📂history
 ┃ ┃ ┃ ┣ 📜annualHistory.html
 ┃ ┃ ┃ ┗ 📜annualHistory.js
 ┣ 📜error.html
 ┣ 📜index.html
 ┗ 📜index.js
 ```
- BackEnd(Spring boot)
```
ManageSystem
 ┣ 📂controller
 ┃ ┣ 📂annual
 ┃ ┃ ┣ 📜AnnualLeaveRequestController.java
 ┃ ┃ ┣ 📜AnnualManageController.java
 ┃ ┃ ┗ 📜AnnualRestController.java
 ┃ ┣ 📂calendar
 ┃ ┃ ┣ 📜CalendarController.java
 ┃ ┃ ┗ 📜CalendarRestController.java
 ┃ ┣ 📂common
 ┃ ┃ ┣ 📜AuthPageController.java
 ┃ ┃ ┗ 📜ErrorPageController.java
 ┃ ┣ 📂department
 ┃ ┃ ┗ 📜DepartmentRestController.java
 ┃ ┣ 📂image
 ┃ ┃ ┗ 📜HeaderController.java
 ┃ ┣ 📂member
 ┃ ┃ ┣ 📜LoginController.java
 ┃ ┃ ┣ 📜LogoutController.java
 ┃ ┃ ┗ 📜MemberController.java
 ┃ ┗ 📂mypage
 ┃ ┃ ┗ 📜AnnualHistoryController.java
 ┣ 📂dao
 ┃ ┣ 📜AnnualCalDAO.java
 ┃ ┣ 📜AnnualCountDAO.java
 ┃ ┣ 📜AnnualDataByFilterDAO.java
 ┃ ┣ 📜AnnualSearchDAO.java
 ┃ ┣ 📜AnnualStatusDAO.java
 ┃ ┣ 📜AnnualSumCountDAO.java
 ┃ ┣ 📜HolidayApiDAO.java
 ┃ ┣ 📜HolidayDbDAO.java
 ┃ ┣ 📜LeaveRequestDAO.java
 ┃ ┣ 📜MemberDAO.java
 ┃ ┗ 📜PageDAO.java
 ┣ 📂dto
 ┃ ┣ 📜AnnualCntDTO.java
 ┃ ┣ 📜AnnualHistoryDTO.java
 ┃ ┣ 📜AnnualManageDTO.java
 ┃ ┣ 📜AnnualSearchDTO.java
 ┃ ┣ 📜CalendarServiceDTO.java
 ┃ ┣ 📜LeaveRequestDTO.java
 ┃ ┣ 📜MemberLoginDTO.java
 ┃ ┣ 📜MemberSearchDTO.java
 ┃ ┣ 📜MemberServiceDTO.java
 ┃ ┗ 📜PageDTO.java
 ┣ 📂repository
 ┃ ┣ 📜AnnualCalRepository.java
 ┃ ┣ 📜AnnualManageRepository.java
 ┃ ┣ 📜AnnualRepository.java
 ┃ ┣ 📜AnnualSumCountRepository.java
 ┃ ┣ 📜DepartmentRepository.java
 ┃ ┣ 📜HolidayRepository.java
 ┃ ┣ 📜LeaveRequestRepository.java
 ┃ ┣ 📜LeaveRequestRepositoryImpl.java
 ┃ ┗ 📜MemberRepository.java
 ┣ 📂service
 ┃ ┣ 📜AnnualManageService.java
 ┃ ┣ 📜AnnualService.java
 ┃ ┣ 📜CalendarService.java
 ┃ ┣ 📜DepartmentService.java
 ┃ ┣ 📜HolidayService.java
 ┃ ┣ 📜LeaveRequestService.java
 ┃ ┗ 📜MemberService.java
 ┣ 📂util
 ┃ ┣ 📂hash
 ┃ ┃ ┗ 📜SHA256.java
 ┃ ┗ 📂interceptor
 ┃ ┃ ┣ 📜AuthInterceptor.java
 ┃ ┃ ┣ 📜LoginInterceptor.java
 ┃ ┃ ┗ 📜WebConfig.java
 ┣ 📂vo
 ┃ ┣ 📜AnnualCalVO.java
 ┃ ┣ 📜AnnualDataByFilterVO.java
 ┃ ┣ 📜AnnualHistoryVO.java
 ┃ ┣ 📜AnnualManageVO.java
 ┃ ┣ 📜AnnualStatusVO.java
 ┃ ┣ 📜HolidayServiceVO.java
 ┃ ┣ 📜MemberInfoVO.java
 ┃ ┗ 📜MemberVO.java
 ┣ 📜ManageSystemApplication.java
 ┗ 📜SwaggerConfig.java
```
<br><br>

<br>
## Git 브랜치 전략

소스코드 작성 및 Git 작업을 시작하기 전에 JIRA 이슈 생성하기
리뷰어에게 꼭 코드리뷰 받기!
Git Pull Request는 리뷰 받고 하기
Jira 이슈넘버로 브랜치 생성하기!



Commit 컨벤션
Jira Smart Commit
<optional COMMAND_ARGUMENTS> <ISSUE_KEY> : <Contents>
optional
feat : 새로운 기능 추가
fix : 버그 수정
docs : 문서 수정
chore : 그 외 자잘한 작업
test : 테스트 코드
build : 시스템 또는 외부 종속성에 영향을 미치는 변경사항 (npm, gulp, yarn 레벨)
ci : CI 관련 설정
style : 코드 의미에 영향을 주지 않는 변경사항 (포맷, 세미콜론 누락, 공백 등)
refactor : 성능 개선
remove: 파일 삭제만 했을 때
rename: 파일 및 폴더 이름만 바꿨을때

규칙
Jira issue 해당이 없으면 생략 가능하다. (chore, style 등 코드 정리를 하는 경우)
커밋 메세지의 마지막에 마침표를 사용하지 않는다.

예시
feat #S06P22D104-2 : 로그인 폼 추가



Branch 컨벤션
Branch 명명 규칙

master : 배포

develop : 개발된 기능(feature)을 통합하는 브랜치

FE/[ISSUE_KEY]/[function name] : 프론트엔드의 각 기능별 개발을 진행하는 브랜치

BE/[ISSUE_KEY]/[function name] : 백엔드의 각 기능별 개발을 진행하는 브랜치


규칙
브랜치 이름은 Jira Epic 제목을 기준으로, 영문 소문자로 작성한다.

예시
FE/#S06P22D104-4/login
BE/#S06P22D104-26/logout



코딩 컨벤션
백엔드

파일명: PascalCase (ex: UserRepository)
패키지명: 소문자
클래스: PascalCase (ex : ClassName)
변수: camelCase (ex : getId, userPassword)
메소드 : camelCase (ex : getId, userPassword)
상수: SNAKE_CASE (ex: FILE_NUMBER)
프론트엔드

변수: camelCase (ex. variableBoolean)

함수: camelCase (ex. const onClickHandler = () => {})

타입: PascalCase

interface만 쓰고 PascalCase로 명명한다.

interface PeopleInterface {
	name: string
	age: number
}
상수: SNAKE_CASE

VSCode

eslint, prettier를 사용한다.
주석은 Better Comments를 사용한다.
TS

컴포넌트/페이지 파일명 : PascalCase
컴포넌트 외 파일명 : camelCase
CSS

css는 module화 하여 사용한다.
id, tag 선택자는 사용하지 않고, class 선택자만 이용한다.
className을 문자로 입력할 때는 PascalCase로 명명한다.
MUI는 피치못할 사정이 있다면 쓴다. Layout은 금지한다.
HTML

가급적 Semantic 태그를 사용한다. (ex : div(x) -> header, nav, section, main ...(o))
