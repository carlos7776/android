const db = require('../config/cofig');

const User = {};

User.getAll = () =>{
    const sql = `
    SELECT
        *
    FROM
        users
    `;
    return db.manyOrNone(sql);
}
module.exports = User;