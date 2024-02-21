package survey;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class SurveyDAO {
	public List<SummaryDTO> listSummary(int survey_idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SummaryDTO> items = session.selectList("survey.list_summary", survey_idx);
		session.close();

		return items;
	}

	public void insertSurvey(AnswerDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("survey.insert_survey", dto);
		session.commit();
		session.close();
	}

	public SurveyDTO viewQuestion(int survey_idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		SurveyDTO dto = session.selectOne("survey.view_question", survey_idx);
		session.close();
		return dto;

	}
}
