# programmers 과제

```
드림어스컴퍼니(FLO)
```
## FLO-API 가이드

- 샘플 데이터는 Spring bean 이 모두 생성된 후 초기화 하는 부분에서 DB 에 추가 (InitDB)
- 입력 데이터 중 'all' 의 경우 해당 데이터 내 에서만 의미가 '모두' 이므로 각각 'ko', 'en', 'ja' 나누어 저장 (나중에 서비스 지역이 추가 될 경우 all 정보가 달라질 수 있으므로)
- 조회에서는 count query 를 따로 분류하여 성능 최적화
- playlist - song 의 관계는 단방향으로 설정



## Response Status Code

| Status Code               | Description                            |
| ------------------------- | -------------------------------------- |
| 200 OK                    | 성공                                   |
| 400 Bad Request           | 클라이언트 요청 오류, 형식이 맞지 않음 |
| 404 NotFound              | 페이지를 찾을 수 없음                  |
| 500 Internal Server Error | 서버에 문제가 있음                     |

## Reference

### 1. 앨범/곡 검색하기

문자열로 앨범/곡을 검색해서 제목이 검색어를 포함하는 앨범과 곡을 찾는 API

#### URL

`/search`

#### Method

`GET`

#### Request Header 구조 예시

```
GET /api/search?locale=ko&title=유재하
Content-Type: application/json
```

#### Query Parameter

| Parameter | Type   | Description                          |
| --------- | ------ | ------------------------------------ |
| title     | String | (필수) 검색어                        |
| locale    | String | (필수) 검색을 요청하는 사용자의 지역 |

#### Sample Call

```
http://localhost:5000/api/search?locale=ko&title=유재하
```

#### Success Response

```
[
  {
    "albums": [
        {
            "id": 133,
            "title": "유재하 <사랑하기 때문에>(1987)",
            "songs": [
                {
                    "id": 134,
                    "title": "유재하 <사랑하기 때문에>(1987) song-1",
                    "track": 1,
                    "length": 165
                },
                {
                    "id": 135,
                    "title": "유재하 <사랑하기 때문에>(1987) song-2",
                    "track": 2,
                    "length": 356
                },
                {
                    "id": 136,
                    "title": "유재하 <사랑하기 때문에>(1987) song-3",
                    "track": 3,
                    "length": 205
                },
                {
                    "id": 137,
                    "title": "유재하 <사랑하기 때문에>(1987) song-4",
                    "track": 4,
                    "length": 222
                },
                ...
            ]
        }
    ],
    "songs": [
        {
            "id": 134,
            "title": "유재하 <사랑하기 때문에>(1987) song-1",
            "track": 1,
            "length": 165
        },
        {
            "id": 135,
            "title": "유재하 <사랑하기 때문에>(1987) song-2",
            "track": 2,
            "length": 356
        },
        ...
    ]
  }
]
```

#### Response Body 구성

| Column | Type   | Description    |
| ------ | ------ | -------------- |
| albums | String | 앨범 검색 결과 |
| songs  | String | 곡 검색 결과   |



### 2. 앨범 리스트 조회

앨범을 10개 단위로 paging

#### URL

`/api/albums`

#### Method

`GET`

#### Request Header 구조 예시

page index 0부터 시작

```
GET /api/albums?locale=ko&page=0
Content-Type: application/json
```

#### Query Parameter

| Parameter | Type   | Description                          |
| --------- | ------ | ------------------------------------ |
| locale    | String | (필수) 검색을 요청하는 사용자의 지역 |
| page      | int    | (필수) 요청할 page                   |

#### Sample Call

```
http://localhost:5000/api/albums?locale=ko&page=0
```

#### Success Response

```
{
    "statusCode": 200,
    "pages": {
        "next": "http://localhost:5000/api/albums?page=1&size=10",
        "last": "http://localhost:5000/api/albums?page=1&size=10"
    },
    "albums": [
        {
            "id": 25,
            "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12)",
            "songs": [
                {
                    "id": 26,
                    "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12) song-1",
                    "track": 1,
                    "length": 334
                },
                {
                    "id": 27,
                    "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12) song-2",
                    "track": 2,
                    "length": 241
                },
                {
                    "id": 28,
                    "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12) song-3",
                    "track": 3,
                    "length": 351
                },
                {
                    "id": 29,
                    "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12) song-4",
                    "track": 4,
                    "length": 293
                },
                {
                    "id": 30,
                    "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12) song-5",
                    "track": 5,
                    "length": 475
                },
                {
                    "id": 31,
                    "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12) song-6",
                    "track": 6,
                    "length": 158
                },
                {
                    "id": 32,
                    "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12) song-7",
                    "track": 7,
                    "length": 351
                },
                {
                    "id": 33,
                    "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12) song-8",
                    "track": 8,
                    "length": 256
                },
                {
                    "id": 34,
                    "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12) song-9",
                    "track": 9,
                    "length": 188
                },
                {
                    "id": 35,
                    "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12) song-10",
                    "track": 10,
                    "length": 139
                },
                {
                    "id": 36,
                    "title": "A Hard Day's Night (1964.7), Beatles for Sale (1964.12) song-11",
                    "track": 11,
                    "length": 284
                }
            ]
        },
        ...
    ]
}
```

#### Response Body 구성

| Column | Type   | Description    |
| ------ | ------ | -------------- |
| statusCode  | int | 상태 코드   |
| pages  | json | 페이징 정보   |
| albums | String | 앨범 검색 결과 |
| songs  | String | 곡 검색 결과   |



### 3. 플레이리스트 API

1) Playlist 생성

#### URL

`/api/playlists`

#### Method

`POST`

#### Request Header 구조 예시

```
POST /api/playlists
Content-Type: application/json
```

#### Request Body

| Parameter | Type   | Description            |
| --------- | ------ | ---------------------- |
| name      | String | (필수) 플레이리스트 명 |
| userId    | int    | (필수) 사용자 id       |

#### Sample Call

```
http://localhost:5000/api/playlists

{
	"name" : "sample_playlist1",
	"userId" : 1
	
}
```

#### Success Response

```
{
    "id": 373
}
```

#### Response Body 구성

| Column | Type | Description         |
| ------ | ---- | ------------------- |
| id     | int  | 플레이리스트 아이디 |



2) Playlist 노래, 앨범 추가

#### URL

`/api/playlists/{id}`

#### Method

`PUT`

#### Request Header 구조 예시

```
PUT /api/playlists/{id}
Content-Type: application/json
```

#### Request Parameter

| Parameter | Type | Description            |
| --------- | ---- | ---------------------- |
| id        | int  | (필수) 플레이리스트 명 |

#### Request Body

| Parameter | Type      | Description             |
| --------- | --------- | ----------------------- |
| album     | int       | (옵션) 앨범 아이디      |
| songs     | List<int> | (옵션) 곡 아이디 리스트 |

#### Sample Call

```
http://localhost:5000/api/playlists/373

{
	"album" : 1,
	"songs" : [20, 30, 40]
	
}
```

#### Success Response

```
{
    "count": 14
}
```

#### Response Body 구성

| Column | Type | Description    |
| ------ | ---- | -------------- |
| count  | int  | 추가한 곡 개수 |



3) Playlist 목록 조회

#### URL

`/api/playlists/{id}`

#### Method

`PUT`

#### Request Header 구조 예시

```
GET /api/playlists/{id}
Content-Type: application/json
```

#### Request Parameter

| Parameter | Type | Description                   |
| --------- | ---- | ----------------------------- |
| id        | int  | (필수) 플레이리스트 사용자 id |

#### Sample Call

```
http://localhost:5000/api/playlists/1
```

#### Success Response

```
{
    "count": 1,
    "playlists": [
        {
            "id": 373,
            "name": "sample_playlist1",
            "userId": 1
        }
    ]
}
```

#### Response Body 구성

| Column    | Type | Description                                                  |
| --------- | ---- | ------------------------------------------------------------ |
| count     | int  | 플레이리스트 개수                                            |
| playlists | list | id : 플레이리스트  아이디,<br />name : 플레이리스트 명,<br />userId : 사용자 아이디 |



4) Playlist 삭제

#### URL

`/api/playlists/{id}`

#### Method

`DELETE`

#### Request Header 구조 예시

```
DELETE /api/playlists/{id}
Content-Type: application/json
```

#### Request Parameter

| Parameter | Type | Description                |
| --------- | ---- | -------------------------- |
| id        | int  | (필수) 플레이리스트 아이디 |

#### Sample Call

```
http://localhost:5000/api/playlists/374
```

#### Success Response

```
{
    "id": 373,
    "name": "sample_playlist1"
}
```

#### Response Body 구성

| Column | Type   | Description         |
| ------ | ------ | ------------------- |
| id     | int    | 플레이리스트 아이디 |
| name   | String | 플레이리스트 명     |

