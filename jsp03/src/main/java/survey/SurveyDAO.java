package survey;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class SurveyDAO {
	public SurveyDTO view(int survey_idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		SurveyDTO dto = session.selectOne("survey.view", survey_idx);
		session.close();
		return dto;
	}

	public void insert(AnswerDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("survey.insert", dto);
		session.commit();
		session.close();
	}

	public List<SummaryDTO> summary(int survey_idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SummaryDTO> items = session.selectList("survey.summary", survey_idx);
		session.close();
		return items;
	}
}
