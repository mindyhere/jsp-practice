package shop;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class CartDAO {
	public List<CartDTO> cart_money() {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<CartDTO> items = session.selectList("cart.product_money");
		session.close();
		return items;
	}

	public void insert_cart(CartDTO dto) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.insert("cart.insert_cart", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<CartDTO> list_cart(String userid) {
		SqlSession session = null;
		List<CartDTO> list = null;
		try {
			session = MybatisManager.getInstance().openSession();
			list = session.selectList("cart.list_cart", userid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	}

	public void delete_cart(int cart_id) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.delete("cart.delete_cart", cart_id);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void update_cart(CartDTO dto) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.update("cart.update_cart", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void clear_cart(String userid) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.delete("cart.clear_cart", userid);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public int sum_money(String userid) {
		int total = 0;
		SqlSession session = MybatisManager.getInstance().openSession();
		total = session.selectOne("cart.sum_money", userid);
		session.close();
		return total;
	}
}
