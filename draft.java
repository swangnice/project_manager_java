
/**
 * @brief 向 members 表中添加一个新成员
 *
 * @param projectId 项目 ID
 * @param name      成员姓名
 * @param role      成员角色
 * @param username  登录用户名
 * @param pwdHash   密码哈希
 * @return          如果插入成功返回 true，否则返回 false
 */
public static boolean addMember(int projectId,
                                String name,
                                String role,
                                String username,
                                String pwdHash) {
    String sql = "INSERT INTO members(project_id, name, role, username, pwd_hash) VALUES(?, ?, ?, ?, ?)";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // 绑定各参数到占位符
        stmt.setInt(1, projectId);
        stmt.setString(2, name);
        stmt.setString(3, role);
        stmt.setString(4, username);
        stmt.setString(5, pwdHash);

        // 执行插入，返回受影响的行数
        int rowsInserted = stmt.executeUpdate();
        return rowsInserted > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}



/**
 * @brief 向 members 表中添加一个新成员
 */
public static boolean addMember(int projectId,
                                String name,
                                String role,
                                String username,
                                String pwdHash) {
    String sql = "INSERT INTO members(project_id, name, role, username, pwd_hash) VALUES(?, ?, ?, ?, ?)";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, projectId);
        stmt.setString(2, name);
        stmt.setString(3, role);
        stmt.setString(4, username);
        stmt.setString(5, pwdHash);
        return stmt.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


/**
 * @brief 根据用户名读取成员全部信息
 */
public static void printMemberInfo(String username) {
    String sql = "SELECT id, project_id, name, role, pwd_hash FROM members WHERE username = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                System.out.printf("ID=%d, 项目ID=%d, 姓名=%s, 角色=%s, 密码哈希=%s%n",
                    rs.getInt("id"),
                    rs.getInt("project_id"),
                    rs.getString("name"),
                    rs.getString("role"),
                    rs.getString("pwd_hash"));
            } else {
                System.out.println("未找到用户名：" + username);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}



/**
 * @brief 更新某个成员的角色
 */
public static boolean updateMemberRole(String username, String newRole) {
    String sql = "UPDATE members SET role = ? WHERE username = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, newRole);
        stmt.setString(2, username);
        return stmt.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


/**
 * @brief 根据用户名删除成员
 */
public static boolean deleteMember(String username) {
    String sql = "DELETE FROM members WHERE username = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        return stmt.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public static void main(String[] args) {
    // Create
    addMember(1, "Alice", "developer", "alice2025", "abcd1234hash");

    // Read
    printMemberInfo("alice2025");

    // Update
    updateMemberRole("alice2025", "lead");

    // Read again to verify update
    printMemberInfo("alice2025");

    // Delete
    deleteMember("alice2025");

    // Read again to verify deletion
    printMemberInfo("alice2025");
}


//===========================================================================
