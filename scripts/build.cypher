CREATE (Machool:_Company {name: 'Machool'})
CREATE (TELUS:_Company {name: 'TELUS'})
CREATE (SAP:_Company {name: 'SAP'})

CREATE (
     Eson:_Person {
          firstName: 'Eson',
          lastName: 'Paguia',
          email: 'eson.paguia@machool.com',
          password: '1234',
          age: 34,
          sex: 'm',
          telephone: '639209531590'
     }
)

CREATE (
     Qiang:_Person {
          firstName: 'Qiang',
          lastName: 'Han',
          email: 'qiang.han@machool.com',
          password: 'asdfg',
          age: 34,
          sex: 'm',
          telephone: '16043519437'
     }
)

CREATE (
     Kamyar:_Person {
          firstName: 'Kamyar',
          lastName: 'Asadibeiky',
          email: 'kamyar.asadibeiky@machool.com',
          password: 'qwert',
          age: 34,
          sex: 'm',
          telephone: '16047275176'
     }
)

CREATE (
     Jorge:_Person {
          firstName: 'Jorge',
          lastName: 'Whittenbury',
          email: 'jorge.whittenbury@machool.com',
          password: '0987',
          age: 34,
          sex: 'm',
          telephone: '16047204262'
     }
)

CREATE
     (Eson)-[:WORK_FOR]->(TELUS),
     (Qiang)-[:WORK_FOR]->(SAP),
     (Kamyar)-[:WORK_FOR]->(Machool),
     (Jorge)-[:WORK_FOR]->(Machool)

