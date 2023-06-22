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
    @Published var coordinates: [Coordinate]? = nil
    
    func initData() {
        self.fetchCoordinate()
    }
    
    func fetchCoordinate() {
        Requests.request("\(API)/emergency/coordinate",
                         .get,
                         [Coordinate].self)
        { data in
            self.coordinates = data
        }
    }
}
