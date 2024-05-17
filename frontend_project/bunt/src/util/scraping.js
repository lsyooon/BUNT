import axios from "axios";
import cheerio from "cheerio";

const log = console.log;

const getHtml = async () => {
    try {
        return await axios.get("https://sports.news.naver.com/kbaseball/record/index?category=kbo");
    } catch (error) {
        console.error(error);
    }
};

const parseHtml = async () => {
    const html = await getHtml();
    if (!html) return;

    const $ = cheerio.load(html.data);
    const $rows = $("#regularTeamRecordList_table tr");

    const teamData = [];

    $rows.each((index, element) => {
        const $columns = $(element).children("td");
        if ($columns.length === 0) return; // Skip header row or rows without enough columns

        const team = {
            rank: $(element).children("th").text().trim(),
            name: $columns.find("span[id^='team_']").text().trim(),
            gamesPlayed: $columns.eq(1).text().trim(),
            wins: $columns.eq(2).text().trim(),
            losses: $columns.eq(3).text().trim(),
            draws: $columns.eq(4).text().trim(),
            winRate: $columns.eq(5).text().trim(),
            gamesBehind: $columns.eq(6).text().trim(),
            recentRecord: $columns.eq(7).text().trim(),
            battingAvg: $columns.eq(8).text().trim(),
            onBasePct: $columns.eq(9).text().trim(),
            last10Games: $columns.eq(10).text().trim(),
        };

        teamData.push(team);
    });

    return teamData;
};

parseHtml().then((data) => log(data));
