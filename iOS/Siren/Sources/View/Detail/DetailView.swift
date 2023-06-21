//
//  DetailView.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import SwiftUI

struct DetailView: View {
    var body: some View {
        VStack(spacing: 7) {
            VStack(alignment: .leading, spacing: 9) {
                HStack(spacing: 0) {
                    Text("경북대학교병원")
                        .font(.system(size: 24, weight: .bold))
                    Spacer()
                    Text("4.7km")
                        .font(.system(size: 16, weight: .bold))
                        .foregroundColor(.white)
                        .padding(.horizontal, 10)
                        .padding(.vertical, 4)
                        .background(Color.accentColor)
                        .clipShape(Capsule())
                }
                HStack(spacing: 0) {
                    Text("대구광역시 중구 동덕로 130 (삼덕동2가)")
                        .foregroundColor(.gray)
                    Spacer()
                    Text("053-200-5100")
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
                HStack {
                    VStack(alignment: .leading, spacing: 5) {
                        Color.gray
                            .frame(width: 24, height: 24)
                        HStack(alignment: .bottom, spacing: 0) {
                            Text("응급실 ")
                                .font(.system(size: 14, weight: .medium))
                            Text("8")
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
                    Text("3분")
                        .font(.system(size: 14, weight: .bold))
                        .foregroundColor(.accentColor)
                    Text("소요 예정")
                        .font(.system(size: 12, weight: .medium))
                }
                Button(action: {
                    
                }) {
                    HStack(spacing: 10) {
                        Text("4.7km 안내 시작")
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

struct DetailView_Previews: PreviewProvider {
    static var previews: some View {
        DetailView()
    }
}
