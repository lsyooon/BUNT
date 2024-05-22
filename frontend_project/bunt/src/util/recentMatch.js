import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

// __filename과 __dirname을 정의합니다.
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const fetchData = async (gameId) => {
  const data = new URLSearchParams({
    'MIME Type': 'application/x-www-form-urlencoded; charset=UTF-8',
    'leId': 1,
    'srId': 0,
    'seasonId': 2024,
    'gameId': gameId
  });

  const urlBaseSc = 'https://www.koreabaseball.com/ws/Schedule.asmx/GetScoreBoardScroll';
  const urlBaseBs = 'https://www.koreabaseball.com/ws/Schedule.asmx/GetBoxScoreScroll';

  try {
    const resSc = await fetch(urlBaseSc, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      },
      body: data
    });

    const resBs = await fetch(urlBaseBs, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      },
      body: data
    });

    const jsonSc = await resSc.json();
    const jsonBs = await resBs.json();

    const table1 = jsonSc['table1'].replace(/\r\n/g, '');
    const table3 = jsonSc['table3'].replace(/\r\n/g, '');

    const dict1 = JSON.parse(table1);
    const dict3 = JSON.parse(table3);

    const date = "2024-05-01"; // 예제 데이터
    const season = "2024"; // 예제 데이터
    const away_name = "Away Team"; // 예제 데이터
    const home_name = "Home Team"; // 예제 데이터

    // away_score1 배열 구성
    const away_score1 = [];
    away_score1.push(date); // 경기 일자
    away_score1.push(season); // 시즌
    away_score1.push(away_name); // 원정팀 이름
    let a = dict1['rows'][0]['row'][1]['Text'].split('>')[3];
    away_score1.push(a); // 원정팀 현재 기록
    let b = dict1['rows'][0]['row'][0]['Text'];
    away_score1.push(b); // 원정팀 해당경기 승/패

    // home_score1 배열 구성
    const home_score1 = [];
    home_score1.push(date);
    home_score1.push(season);
    home_score1.push(home_name); // 홈팀 이름
    a = dict1['rows'][1]['row'][1]['Text'].split('>')[3];
    home_score1.push(a); // 홈팀 현재 기록
    b = dict1['rows'][1]['row'][0]['Text'];
    home_score1.push(b); // 홈팀 해당경기 승/패

    // away_score3, home_score3 배열 구성
    const away_score3 = [];
    const home_score3 = [];
    for (let i = 0; i < 4; i++) { // 스코어, 안타수, 에러수, 볼넷 수 반복문
      a = dict3['rows'][0]['row'][i]['Text'];
      away_score3.push(a); // 'append' -> 'push'로 변경
      b = dict3['rows'][1]['row'][i]['Text'];
      home_score3.push(b); // 'append' -> 'push'로 변경
    }

    // DataFrame 유사 객체 구성
    const score_board1 = {
      Date: [away_score1[0], home_score1[0]],
      Season: [away_score1[1], home_score1[1]],
      Team: [away_score1[2], home_score1[2]],
      Record: [away_score1[3], home_score1[3]],
      Result: [away_score1[4], home_score1[4]]
    };

    const score_board3 = {
      Score: [away_score3[0], home_score3[0]],
      Hit: [away_score3[1], home_score3[1]],
      Error: [away_score3[2], home_score3[2]],
      Base_on_balls: [away_score3[3], home_score3[3]]
    };

    // 두 테이블에서 가져온 정보 합치기
    const score_board = {
      Date: score_board1.Date,
      Season: score_board1.Season,
      Team: score_board1.Team,
      Record: score_board1.Record,
      Result: score_board1.Result,
      Score: score_board3.Score,
      Hit: score_board3.Hit,
      Error: score_board3.Error,
      Base_on_balls: score_board3.Base_on_balls
    };

    // JSON 파일로 저장
    const filePath = path.join(__dirname, 'recentMatch.json');
    fs.writeFileSync(filePath, JSON.stringify(score_board, null, 2), 'utf-8');
    console.log('Data saved to recentMatch.json');

  } catch (error) {
    console.error('Error:', error);
  }
}

// 외부에서 gameId를 받아서 fetchData 함수 호출
const gameId = '20240501WOLT0'; // 예제 gameId
fetchData(gameId);
