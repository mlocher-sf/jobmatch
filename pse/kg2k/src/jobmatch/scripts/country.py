import jobmatch
import jobmatch.business.entity

ENTITY = jobmatch.business.entity
Country = ENTITY.Country

Items = ['Afghanistan', 'Belgien', 'Schweiz', 'Italien', 'USA', 'Zaire' ]

for item in Items:
    print Country(item)
