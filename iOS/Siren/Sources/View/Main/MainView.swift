//
//  MainView.swift
//  Siren
//
//  Created by Mercen on 2023/06/21.
//

import SwiftUI
import CoreLocation

struct MainView: View {

    @StateObject private var locationManager = LocationManager()
    @StateObject private var viewModel = MainViewModel()
    
    var body: some View {
        GeometryReader { outsideProxy in
            if viewModel.active {
                NavView(active: $viewModel.active,
                        startDest: "현재 위치",
                        startX: viewModel.startX,
                        startY: viewModel.startY,
                        endDest: "경북대학교",
                        endX: 344899.540357,
                        endY: 264387.239136)
                    .ignoresSafeArea()
            } else {
                ZStack {
                    if let coordinates = viewModel.coordinates {
                        MapView(coordinates: coordinates)
                            .ignoresSafeArea()
                    }
                    VStack {
                        Spacer()
                        ScrollView(.horizontal, showsIndicators: false) {
                            HStack(spacing: 10) {
                                ForEach(0..<5) { idx in
                                    VStack {
                                        Spacer()
                                        GeometryReader { insideProxy in
                                            HStack(alignment: .bottom, spacing: 10) {
                                                Color.gray
                                                    .scaledToFill()
                                                    .frame(width: 86, height: 86)
                                                    .cornerRadius(15)
                                                ZStack(alignment: .bottomLeading) {
                                                    VStack(alignment: .leading, spacing: 3) {
                                                        HStack(spacing: 4) {
                                                            Text("경북대학교병원")
                                                                .font(.system(size: 16, weight: .bold))
                                                            Text("4.7km")
                                                                .font(.system(size: 12, weight: .bold))
                                                                .foregroundColor(.white)
                                                                .padding(.horizontal, 5)
                                                                .padding(.vertical, 2.7)
                                                                .background(Color.accentColor)
                                                                .clipShape(Capsule())
                                                        }
                                                        Text("대구광역시 중구 동덕로 130 (삼덕동2가)")
                                                            .font(.system(size: 12, weight: .medium))
                                                            .foregroundColor(.gray)
                                                        Spacer()
                                                    }
                                                    HStack(alignment: .bottom, spacing: 3) {
                                                        Text("약")
                                                            .font(.system(size: 12, weight: .medium))
                                                        Text("3분")
                                                            .font(.system(size: 14, weight: .bold))
                                                            .foregroundColor(.accentColor)
                                                        Text("소요 예정")
                                                            .font(.system(size: 12, weight: .medium))
                                                    }
                                                }
                                                .padding(.vertical, 8)
                                                Spacer()
                                                Button(action: {
                                                    let value = KNSDK.sharedInstance()!
                                                        .convertWGS84ToKATEC(withLongitude: locationManager.userLongitude,
                                                                             latitude: locationManager.userLatitude)
                                                    viewModel.startX = CGFloat(value.x)
                                                    viewModel.startY = CGFloat(value.y)
                                                    viewModel.active = true
                                                }) {
                                                    Image("Path")
                                                        .foregroundColor(.white)
                                                        .scaledToFit()
                                                        .frame(width: 18)
                                                        .frame(width: 50, height: 50)
                                                        .background(Color.accentColor)
                                                }
                                                .clipShape(Circle())
                                            }
                                            .padding(8)
                                            .background(Color.white)
                                            .cornerRadius(20)
                                            .offset(y: { () -> CGFloat in
                                                let outsideFrame = outsideProxy.frame(in: .global)
                                                let insideFrame = insideProxy.frame(in: .global)
                                                let minus = abs(outsideFrame.minX - insideFrame.minX + 20)
                                                return minus / outsideFrame.width * 22 - 22
                                            }())
                                        }
                                        .frame(width: outsideProxy.frame(in: .global).width - 40,
                                               height: 102)
                                    }
                                    .id(idx)
                                }
                                .frame(height: 124)
                            }
                            .padding(.horizontal, 20)
                        }
                    }
                    .shadow(color: .black.opacity(0.2), radius: 2, y: 2)
                    .shadow(color: .black.opacity(0.1), radius: 6, y: 6)
                }
            }
        }
        .onAppear(perform: viewModel.initData)
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
