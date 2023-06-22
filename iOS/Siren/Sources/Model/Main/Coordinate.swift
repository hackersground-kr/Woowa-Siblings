//
//  Coordinate.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import Foundation

struct Coordinate: Codable {
    let wgs84Lon, wgs84Lat: Double
    let rnum, phpid, hpid, dutyTel1,
        dutyName, dutyEmclsName,
        dutyEmcls, dutyAddr: String
    let dutyTel3: String?
}
