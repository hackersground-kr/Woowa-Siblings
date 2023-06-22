# `우아한 남매들` - `사이렌`

해커그라운드 해커톤에 참여하는 `우아한 남매들` 팀의 `사이렌`입니다.

## 참고 문서

> 아래 두 링크는 해커톤에서 앱을 개발하면서 참고할 만한 문서들입니다. 이 문서들에서 언급한 서비스 이외에도 더 많은 서비스들이 PaaS, SaaS, 서버리스 형태로 제공되니 참고하세요.

- [순한맛](./REFERENCES_BASIC.md)
- [매운맛](./REFERENCES_ADVANCED.md)

## 제품/서비스 소개

<!-- 아래 링크는 지우지 마세요 -->
[제품/서비스 소개 보기](TOPIC.md)
<!-- 위 링크는 지우지 마세요 -->

## 오픈 소스 라이센스

<!-- 아래 링크는 지우지 마세요 -->
[오픈소스 라이센스 보기](./LICENSE)
<!-- 위 링크는 지우지 마세요 -->

## 설치 방법

> **아래 제공하는 설치 방법을 통해 심사위원단이 여러분의 제품/서비스를 실제 Microsoft 애저 클라우드에 배포하고 설치할 수 있어야 합니다. 만약 아래 설치 방법대로 따라해서 배포 및 설치가 되지 않을 경우 본선에 진출할 수 없습니다.**

## Server

- 기본적인 Java 실행에 필요한 것들을 준비해놓아야 함 [https://www.oracle.com/kr/]
- Git을 준비해놓아야함 [https://git-scm.com/]

## iOS

![step1](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step1.png)
### 1단계
Visual Studio App Center에서 로그인 후 Add new app 버튼을 누릅니다. 그리고 사진과 같이 입력하고 선택한 후 우측 하단 버튼을 누릅니다. 

![step2](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step2.png)
### 2단계
GitHub버튼을 누릅니다.

![step3](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step3.png)
### 3단계
Woowa-Siblings 레포지토리를 선택합니다.

![step4](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step4.png)
### 4단계
main-ios 브랜치를 선택합니다.

![step5](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step5.png)
### 5단계
Configure Build 버튼을 누릅니다.

![step6](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step6.png)
### 6단계
Siren.xcworkspace가 선택된 것을 확인합니다.

![step7](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step7.png)
### 7단계
Sign builds를 활성화하고, Provisioning Profile과 Certificate 파일을 업로드합니다. 

![step8](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step8.png)
### 8단계
Test on a real device를 활성화하고, Distribute builds에서 Groups를 선택합니다. 그리고 우측 하단의 Save & Build 버튼을 누릅니다.

![step9](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step9.png)
### 9단계
Release에서 빌드된 릴리즈를 선택합니다.

![step10](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step10.png)
### 10단계
Distribute 버튼을 누릅니다.

![step11](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step11.png)
### 11단계
두 단계를 스킵하고, 우측 하단의 Distribute 버튼을 누릅니다. 

![step12](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step12.png)
### 12단계
우측 상단의 휴대폰 버튼을 누르면 앱을 설치할 준비가 끝납니다. 

![step13](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step13.png)
### 13단계
iOS 디바이스로 화면에 나온 QR코드를 인식하고 로그인하면 나오는 이 페이지에서 Add Device 버튼을 누릅니다. 

![step14](https://github.com/hackersground-kr/Woowa-Siblings/blob/87798e57811869814eaa46c8e8e52f0a706c823b/images/iOS/Step14.png)
### 14단계
프로파일을 설정한 후 브라우저로 돌아와 Install 버튼을 누르면 앱이 설치됩니다. 

### 사전 준비 사항

> **여러분의 제품/서비스를 Microsoft 애저 클라우드에 배포하기 위해 사전에 필요한 준비 사항들을 적어주세요.**
> > - iOS : 
> - android :
- Microsoft Azure 계정
- 안드로이드 빌드 aab or apk 파일
- Github 계정
> - backend :
- Spring Boot Project (작성자 기준 : Gradle-Groovy, Java, Spring Boot 3.1.0, 패키징 war, JDK 17)
- main-server와 dev-server로 branch를 나눈 Git 프로젝트, hackersground 제출에 필요한 파일들과 server 폴더, server 폴더 안에 Spring Boot Project가 들어가 있어야함

## 시작하기

> **여러분의 제품/서비스를 Microsoft 애저 클라우드에 배포하기 위한 절차를 구체적으로 나열해 주세요.**
> > - iOS :

> android :
1. [App Center](https://appcenter.ms/apps) 에 접속해서 로그인한다.
2. Add new app 버튼을 누르고 `App name`, `Release Type`, `Icon`, `OS`, `Platform`을 선택한 후 Add new app을 클릭한다.
3. 안드로이드 프로젝트의 app/build.gralde에 아래 epicenter dependency를 추가한 뒤 sync 한다.
```
dependencies {
    def appCenterSdkVersion = '4.4.5'
    implementation "com.microsoft.appcenter:appcenter-analytics:${appCenterSdkVersion}"
    implementation "com.microsoft.appcenter:appcenter-crashes:${appCenterSdkVersion}"
}

```
4. MainActivity의 `onCreate()` 에 아래 코드를 추가한다.
두번째 인자에는 App Center에서 발급해준 `app secret`을 적는다.
``` 
AppCenter.start(application, "{Your app secret here}",
                  Analytics.class, Crashes.class);
```
5. 안드로이드 프로젝트에서 Generate Signed Bundle or Apk를 클릭해서 Android App Bundle을 선택한 후 NEXT를 누른다.
6. 프로젝트의 Key store path를 등록하고 Key password와 Key Store password를 입력한 후 NEXT를 누른다.
7. `release`를 선택한 후 Create를 눌러 aab 파일을 생성한다.
8. App Center의 Distribute에 들어가서 New release를 클릭한 후 app/release 디렉터리에 있는 aab 파일을 등록한다.
> - backend :

Azure App Service에 가서, 새로운 웹 앱을 만듭니다.
본인의 사정에 맞게 기본 설정을 합니다. (본인은 게시 : 코드, 런타임 스택 : Java 17, 운영 체제 : Linux로 설정)
배포 탭에 가서 Github Actions 설정 밑에 지속적인 배포 사용을 선택하고, 본인의 계정, 레포지토리, 브랜치를 선택합니다.
그 후 검토 후 만들기를 선택합니다.
Azure App Service의 배포가 완료되었다면, 본인이 만든 App Service의 개요에서 게시 프로필 다운로드를 미리 받아놓습니다.
그 후, 구성에 들어가 일반 설정에 들어가면, Java 웹 서버를 Apache Tomcat 10.0으로 세팅합니다.
시작 명령에 java -jar /home/site/wwwroot/{your_file_name}.war --server.port=80를 추가합니다
본인의 Github Repository에 들어가, 프로젝트 폴더에 있는 .github/workflows에 있는 yml 파일을 엽니다.
본인의 상황에 맞게 yml을 작성합니다. (본인은 main, main-server, dev-server 브랜치를 나눠서 개발했기 때문에, main-server에 Push가 되면 Deploy가 되도록 했습니다)
참고 자료 : [https://github.com/hackersground-kr/Woowa-Siblings/blob/main-server/.github/workflows/main-server_siren.yml]
Deploy to Azure Web App 부분이 중요한데, Azure에 zip 파일안에 war 파일을 담아서 보내는 부분입니다.
${{ secrets.AZUREAPPSERVICE_PUBLISHPROFILE_7E99AD0C1D8B49DE8F51757891AAE21F }} 부분은 배포를 하기 위한 게시 프로필을 올려놓는 곳 입니다.
본인의 프로젝트에 가서, Setting -> Secrets and variables -> Actions에 들어갑니다.
New Repository Secret을 누르고, Name에 secrets를 기입한 뒤, 아까 다운로드 받아놓은 게시 프로필의 내용을 Secret에 그대로 붙여넣습니다.
