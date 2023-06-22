//
//  Requests.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import Alamofire
import Foundation

class Requests {
    static func simple(_ url: String,
                       _ method: HTTPMethod,
                       params: [String: Any]? = nil,
                       completion: @escaping () -> Void) {
        AF.request(url,
                   method: method,
                   parameters: params,
                   encoding: method == .get ? URLEncoding.default : JSONEncoding.default,
                   interceptor: Interceptor()
        )
        .validate()
        .responseData { response in
            if let resdata = response.data {
                print(String(decoding: resdata, as: UTF8.self))
            }
            switch response.result {
            case .success:
                completion()
            case .failure:
                print("error")
            }
        }
    }
    
    static func request<T: Codable>(_ url: String,
                                    _ method: HTTPMethod,
                                    params: [String: Any]? = nil,
                                    _ model: T.Type,
                                    completion: @escaping (T) -> Void) {
        AF.request(url,
                   method: method,
                   parameters: params,
                   encoding: method == .get ? URLEncoding.default : JSONEncoding.default,
                   headers: ["Authorization": "KakaoAK"],
                   interceptor: Interceptor()
        )
        .validate()
        .responseData { response in
            if let resdata = response.data {
                print(String(decoding: resdata, as: UTF8.self))
            }
            switch response.result {
            case .success:
                if let data = response.data {
                    let decoder = JSONDecoder()
                    let dateFormatter = DateFormatter()
                    dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
                    decoder.dateDecodingStrategy = .formatted(dateFormatter)
                    //let decodedData = try! decoder.decode(Response<T>.self, from: data)
                    if let decodedData = try? decoder.decode(T.self, from: data) {
                        DispatchQueue.main.async {
                            completion(decodedData)
                        }
                    }
                }
            case .failure:
                print("error")
            }
        }
    }
}
