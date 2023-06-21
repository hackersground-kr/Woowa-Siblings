//
//  MainView.swift
//  Siren
//
//  Created by Mercen on 2023/06/21.
//

import SwiftUI

struct MainView: View {
    var body: some View {
        VStack {
            HStack(spacing: 10) {
                Color.gray
                    .scaledToFill()
                    .frame(width: 86, height: 86)
                    .cornerRadius(15)
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
                }
                Spacer()
            }
            .padding(8)
            .background(Color.white)
            .cornerRadius(20)
            .shadow(color: .black.opacity(0.2), radius: 2, y: 2)
            .shadow(color: .black.opacity(0.1), radius: 6, y: 6)
        }
        .padding(20)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.accentColor)
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
