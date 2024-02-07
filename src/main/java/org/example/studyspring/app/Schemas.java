package org.example.studyspring.app;

record DefaultResponse(boolean error, String message, Object[] payload) {
}

record RoleItem(Long id, String name) {
}

record AddRoleRequest(String name) {
}

record UserItem(Long id, String username, String password, RoleItem role) {
}

record AddUserRequest(String username, String password, int roleId) {
}

record DeleteUserRequest(int userId) {
}

record DeleteRoleRequest(int roleId) {
}