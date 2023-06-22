//
//  DetailView.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import SwiftUI

struct DetailView: View {
    
    @EnvironmentObject var viewModel: MainViewModel
    @EnvironmentObject var locationManager: LocationManager
    
    let data: Emergency
    let coordinate: Coordinate
    let room = ["응급실", "수술실", "일반중환자실", "흉부중환자실", "내과중환자실", "신경중환자실", "신생아중환자실", "입원실", "외과입원실", "신경과입원실", "신경외과중환자실"]
    func getRoomInfo() -> [String] {
        [data.emergencyRoom, data.operatingRoom, data.geIntensiveCareUnit, data.thIntensiveCareUnit, data.intensiveCareUnit, data.neurologicalIntensiveCareUnit,
         data.neonatalIntensiveCareUnit, data.neurosurgeryIntensiveCareUnit, data.inpatientRoom, data.surgicalInpatientRoom, data.neurologyInpatientRoom]
    }
    
    var body: some View {
        VStack(spacing: 7) {
            VStack(alignment: .leading, spacing: 9) {
                HStack(spacing: 0) {
                    Text(data.hospitalName)
                        .font(.system(size: 24, weight: .bold))
                    Spacer()
                    Text(\(viewModel.getInfo(locationManager.userLatitude, locationManager.userLongitude,
                                             coordinate.wgs84Lon, coordinate.wgs84Lat)[0]))
                        .font(.system(size: 16, weight: .bold))
                        .foregroundColor(.white)
                        .padding(.horizontal, 10)
                        .padding(.vertical, 4)
                        .background(Color.accentColor)
                        .clipShape(Capsule())
                }
                HStack(spacing: 0) {
                    Text(coordinate.dutyAddr)
                        .foregroundColor(.gray)
                    Spacer()
                    Text(coordinate.dutyTel1)
                        .underline()
                        .foregroundColor(.accentColor)
                }
                .font(.system(size: 14, weight: .medium))
                Text("남은 병실")
                    .font(.system(size: 20, weight: .bold))
                    .padding(.top, 21)
            }
            .padding(.horizontal, 20)
            
            ScrollView(.horizontal, showsIndicators: false) {
                ForEach(zip(room, getRoomInfo()), id: \.self) { item in
                    HStack {
                        VStack(alignment: .leading, spacing: 5) {
                            Color.gray
                                .frame(width: 24, height: 24)
                            HStack(alignment: .bottom, spacing: 0) {
                                Text("\(item[0]) ")
                                    .font(.system(size: 14, weight: .medium))
                                Text("\(item[1])")
                                    .font(.system(size: 14, weight: .bold))
                                    .foregroundColor(.accentColor)
                                Text("자리")
                                    .font(.system(size: 14, weight: .medium))
                            }
                        }
                        .padding(.horizontal, 11)
                        .padding(.vertical, 5)
                        .overlay(RoundedRectangle(cornerRadius: 10)
                            .strokeBorder(Color.gray.opacity(0.5), lineWidth: 1))
                    }
                }
                .padding(.horizontal, 20)
            }
            .padding(.top, 3)
            Spacer()
            Rectangle()
                .fill(.gray.opacity(0.5))
                .frame(height: 1)
            VStack(spacing: 5) {
                HStack(alignment: .bottom, spacing: 3) {
                    Text("약")
                        .font(.system(size: 12, weight: .medium))
                    Text(viewModel.getInfo(locationManager.userLatitude, locationManager.userLongitude,
                                           coordinate.wgs84Lon, coordinate.wgs84Lat)[1])
                    .font(.system(size: 14, weight: .bold))
                    .foregroundColor(.accentColor)
                    Text("소요 예정")
                        .font(.system(size: 12, weight: .medium))
                }
                Button(action: {
                    viewModel.selected = nil
                    viewModel.active = true
                }) {
                    HStack(spacing: 10) {
                        Text("\(viewModel.getInfo(locationManager.userLatitude, locationManager.userLongitude,
                                           coordinate.wgs84Lon, coordinate.wgs84Lat)[0]) 안내 시작")
                        .foregroundColor(.white)
                        .font(.system(size: 20, weight: .bold))
                        Image("Path")
                            .foregroundColor(.white)
                            .scaledToFit()
                            .frame(width: 18)
                    }
                    .padding(.vertical, 13)
                    .frame(maxWidth: .infinity)
                    .background(Color.accentColor)
                }
                .cornerRadius(10)
            }
            .padding(.horizontal, 20)
        }
    }
}
