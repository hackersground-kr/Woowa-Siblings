//
//  MapVC.m
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

#import <UIKit/UIKit.h>
#import <AVKit/AVKit.h>
#import <KNSDK/KNSDK.h>
#import <KNSDK/KNNaviView.h>

@interface MapVC : UIViewController

@property KNNaviView *naviView;

@end

@implementation MapVC

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [[KNSDK sharedInstance] initializeWithAppKey:@"165dc5c6c3dfe3ac14491057c95a91bc" clientVersion:@"1.0" userKey:@"1" completion:^(KNError *error) {
        if (error)
        {
            NSLog(@"KNSDK Init Failed(%@, %@)", error.code, error.msg);
        }
        else
        {
            NSLog(@"인증 성공");
            AVAudioSession *session = [AVAudioSession sharedInstance];
            NSError *error = nil;
            [session setActive:NO error:&error];
            AVAudioSessionCategoryOptions category = AVAudioSessionCategoryOptionMixWithOthers;
            [[AVAudioSession sharedInstance] setMode:AVAudioSessionModeDefault error:nil];
            [[AVAudioSession sharedInstance] setCategory:AVAudioSessionCategoryPlayback withOptions:category error:&error];
            [session setActive:YES error:&error];
            
            // 출발지 설정
            KNPOI *start = [[KNPOI alloc] initWithName:@"EXCO" x:345585.0000016 y:267122.999974];
            // 목적지 설정
            KNPOI *goal = [[KNPOI alloc] initWithName:@"서문시장" x:344661.0000015 y:264974.999974];
            // 경로 생성
            [KNSDK.sharedInstance makeTripWithStart:start goal:goal vias:[NSArray new] completion:^(KNError * aError, KNTrip * aTrip) {
                if(aError)
                {
                    // 경로 생성 실패
                    NSLog(@"Generation Failed(%@, %@)", aError.code, aError.msg);
                }
                else
                {
                    KNRoutePriority routePriority = routePriority;
                    KNRouteAvoidOption routeAvoidOption = routeAvoidOption;
                    // 경로 요청
                    [aTrip routeWithPriority:routePriority avoidOptions:routeAvoidOption completion:^(KNError * _Nullable aError, NSArray<KNRoute *> * _Nullable aRoutes) {
                        if(aError)
                        {
                            // 경로 요청 실패
                            NSLog(@"Request Failed(%@, %@)", aError.code, aError.msg);
                        }
                        else
                        {
                            // 경로 요청 성공
                            KNGuidance *guidance = [[KNSDK  sharedInstance] sharedGuidance];
                            
                            // 각 가이던스 델리게이트 등록
                            guidance.guideStateDelegate = self;
                            guidance.routeGuideDelegate = self;
                            guidance.voiceGuideDelegate = self;
                            guidance.safetyGuideDelegate = self;
                            guidance.locationGuideDelegate = self;
                            guidance.citsGuideDelegate = self;
                            
                            // 주행 UI 생성
                            self->_naviView = [[KNNaviView alloc] initWithGuidance:guidance trip:nil routeOption:routePriority avoidOption:routeAvoidOption];
                            self->_naviView.frame = self.view.bounds;
                            self->_naviView.guideStateDelegate = self;
                            self->_naviView.stateDelegate = self;
                            [self->_naviView sndVolume:1];
                            [self.view addSubview:self->_naviView];
                        }
                    }];
                }
            }];
        }
    }];
}

// KNGuidance_GuideStateDelegate

// 길 안내 시작 시 호출
- (void)guidanceGuideStarted:(KNGuidance *)aGuidance {
    [self->_naviView guidanceGuideStarted:aGuidance];
}
 
// 경로 변경 시 호출. 교통 변화 또는 경로 이탈로 인한 재탐색 및 사용자 재탐색 시 전달
- (void)guidanceCheckingRouteChange:(KNGuidance *)aGuidance {
    [self->_naviView guidanceCheckingRouteChange:aGuidance];
}
 
// 경로에서 이탈한 뒤 새로운 경로를 요청할 때 호출
- (void)guidanceOutOfRoute:(KNGuidance *)aGuidance {
    [self->_naviView guidanceOutOfRoute:aGuidance];
}
 
// 수신 받은 새 경로가 기존의 안내된 경로와 동일할 경우 호출
- (void)guidanceRouteUnchanged:(KNGuidance *)aGuidance {
    [self->_naviView guidanceRouteUnchanged:aGuidance];
}
 
// 경로에 오류가 발생 시 호출
- (void)guidance:(KNGuidance *)aGuidance routeUnchangedWithError:(KNError *)aError {
    [self->_naviView guidance:aGuidance routeUnchangedWithError:aError];
}
 
// 수신 받은 새 경로가 기존의 안내된 경로와 다를 경우 호출. 여러 개의 경로가 있을 경우 첫 번째 경로를 주행 경로로 사용하고 나머지는 대안 경로로 설정됨
- (void)guidanceRouteChanged:(KNGuidance *)aGuidance {
    [self->_naviView guidanceRouteChanged:aGuidance];
}
 
// 길 안내 종료 시 호출
- (void)guidanceGuideEnded:(KNGuidance *)aGuidance {
    [self->_naviView guidanceGuideEnded:aGuidance isShowDriveResultDialog:YES];
};
 
// 주행 중 기타 요인들로 인해 경로가 변경되었을 때 호출
- (void)guidance:(KNGuidance *)aGuidance didUpdateRoutes:(NSArray<KNRoute *> *)aRoutes multiRouteInfo:(KNMultiRouteInfo * _Nullable)aMultiRouteInfo {
    [self->_naviView guidance:aGuidance didUpdateRoutes:aRoutes multiRouteInfo:aMultiRouteInfo];
}


// KNGuidance_LocationGuideDelegate

// 위치 정보가 변경될 경우 호출. `aLocationGuide`의 항목이 1개 이상 변경 시 전달됨.
- (void)guidance:(KNGuidance * _Nonnull)aGuidance didUpdateLocation:(KNGuide_Location * _Nonnull)aLocationGuide {
    [self->_naviView guidance:aGuidance didUpdateLocation:aLocationGuide];
}


// KNGuidance_RouteGuideDelegate
 
// 경로 안내 정보 업데이트 시 호출. `aRouteGuide`의 항목이 1개 이상 변경 시 전달됨.
- (void)guidance:(KNGuidance * _Nonnull)aGuidance didUpdateRouteGuide:(KNGuide_Route * _Nonnull)aRouteGuide {
    [self->_naviView guidance:aGuidance didUpdateRouteGuide:aRouteGuide];
}

// KNGuidance_SafetyGuideDelegate
 
// 안전 운행 정보 업데이트 시 호출. `aSafetyGuide`의 항목이 1개 이상 변경 시 전달됨.
- (void)guidance:(KNGuidance * _Nonnull)aGuidance didUpdateSafetyGuide:(KNGuide_Safety * _Nonnull)aSafetyGuide {
    [self->_naviView guidance:aGuidance didUpdateSafetyGuide:aSafetyGuide];
}
 
// 주변의 안전 운행 정보 업데이트 시 호출
- (void)guidance:(KNGuidance * _Nonnull)aGuidance didUpdateAroundSafeties:(NSArray<__kindof KNSafety *> * _Nullable)aSafeties {
    [self->_naviView guidance:aGuidance didUpdateAroundSafeties:aSafeties];
}


// KNGuidance_VoiceGuideDelegate

// 음성 안내 사용 여부
- (BOOL)guidance:(KNGuidance * _Nonnull)aGuidance shouldPlayVoiceGuide:(KNGuide_Voice * _Nonnull)aVoiceGuide replaceSndData:(NSData **)aNewData{
    return [self->_naviView guidance:aGuidance shouldPlayVoiceGuide:aVoiceGuide replaceSndData:aNewData];
}
 
// 음성 안내 시작
- (void)guidance:(KNGuidance * _Nonnull)aGuidance willPlayVoiceGuide:(KNGuide_Voice * _Nonnull)aVoiceGuide {
    [self->_naviView guidance:aGuidance willPlayVoiceGuide:aVoiceGuide];
}
 
// 음성 안내 종료
- (void)guidance:(KNGuidance * _Nonnull)aGuidance didFinishPlayVoiceGuide:(KNGuide_Voice * _Nonnull)aVoiceGuide {
    [self->_naviView guidance:aGuidance didFinishPlayVoiceGuide:aVoiceGuide];
}


// KNGuidance_CitsGuideDelegate
 
// C-ITS 정보 변경 시 호출
- (void)guidance:(KNGuidance * _Nonnull)aGuidance didUpdateCitsGuide:(KNGuide_Cits * _Nonnull)aCitsGuide {
    [self->_naviView guidance:aGuidance didUpdateCitsGuide:aCitsGuide];
}


@end
