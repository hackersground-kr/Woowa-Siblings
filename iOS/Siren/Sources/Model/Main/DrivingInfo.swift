//
//  DrivingInfo.swift
//  Siren
//
//  Created by Mercen on 2023/06/23.
//

import Foundation

struct DrivingInfo: Codable {
    let code, message, currentDateTime: Int?
    let route: DrivingRoute
}

struct DrivingRoute: Codable {
    let summary: DrivingSummary
    let path, section, guide: Int?
}

struct DrivingSummary: Codable {
    let start, goal, waypoints, bbox, tollFare, taxiFare, fuelPrice: Int?
    let distance, duration: Int
}
