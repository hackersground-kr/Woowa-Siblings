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

### 사전 준비 사항

> **여러분의 제품/서비스를 Microsoft 애저 클라우드에 배포하기 위해 사전에 필요한 준비 사항들을 적어주세요.**
> > - iOS : 
> - android : 
> - backend : 

## 시작하기

> **여러분의 제품/서비스를 Microsoft 애저 클라우드에 배포하기 위한 절차를 구체적으로 나열해 주세요.**
> > - iOS :

> android :
- Microsoft Azure 계정
- 안드로이드 빌드 aab or apk 파일
- Github 계정
Microsoft App Center에 사전 준비 사항
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
