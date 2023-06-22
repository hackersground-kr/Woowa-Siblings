//
//  MainViewModel.swift
//  Siren
//
//  Created by Mercen on 2023/06/21.
//

import Combine
import CoreLocation

class MainViewModel: ObservableObject {
    
    @Published var searchText: String = ""
    @Published var active: Bool = false
    @Published var startX: CGFloat = 0
    @Published var startY: CGFloat = 0
    @Published var coordinates: [Coordinate] = []
    
    func initData() {
        self.fetchCoordinate()
    }
    
    func fetchCoordinate() {
        Requests.request("\(API)/emergency/coordinate",
                         .get,
                         Response<[Coordinate]>.self)
        { data in
            self.coordinates = data.data
        }
    }
    
    func getInfo(_ startX: CGFloat, _ startY: CGFloat,
                 _ endX: CGFloat, _ endY: CGFloat)
    {
        print(["origin": "\(startX),\(startY)0", "destination": "\(endX),\(endY)"])
        Requests.request("https://apis-navi.kakaomobility.com/v1/directions",
                         .get,
                         params: ["origin": "\(startX),\(startY)", "destination": "\(endX),\(endY)"],
                         DrivingInfo.self)
        { data in
            print(data)
        }
    }
}
