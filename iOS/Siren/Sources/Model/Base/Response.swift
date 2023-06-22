//
//  Response.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import Foundation

class Response<T: Codable>: Codable {
    var status: Int
    var message: String
    var data: T
}
