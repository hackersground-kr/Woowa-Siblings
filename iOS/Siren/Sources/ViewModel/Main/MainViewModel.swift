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
    @Published var selected: Coordinate? = nil
    
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
        Requests.request("https://apis-navi.kakaomobility.com/v1/directions",
                         .get,
                         params: ["origin": "\(startX),\(startY)", "destination": "\(endX),\(endY)"],
                         DrivingInfo.self) -> [Int]
        { data in
            return [data.route.summary.distance, data.route.summary.duration]
        }
    }
}
