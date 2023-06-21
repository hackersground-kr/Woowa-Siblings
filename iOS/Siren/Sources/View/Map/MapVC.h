//
//  MapVC.h
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

#import <UIKit/UIKit.h>

@interface MapVC : UIViewController

- (void)startNavigateWithStartDest:(NSString *)startDest
                            startX:(CGFloat)startX
                            startY:(CGFloat)startY
                           endDest:(NSString *)endDest
                              endX:(CGFloat)endX
                              endY:(CGFloat)endY
                          activate:(void (^)(bool))activate;

@end
