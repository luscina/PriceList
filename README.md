#Lenovo Price List

Lenovo price list is Java based web application that transfors publicly avilable Lenovo Price List Excel Price List for Polish market.
It also combines information with OCM (Options Compatibility Matrix) Excel file.
This application allows to browse through pricelist positions and search for additional compatible components (e.g. docking stations, RAM memory, SSD storage) and services options (warranty extensions) for system units (notebooks, desktops).
There are avilable offical Lenovo services that provides similiar services but those are not adjust for Polish market often offering solutins not avilable in region.

#Access

```bash
https://pricelist.herokuapp.com/swagger-ui/index.html
```

login information:

user: admin
password: admin

## Usage

#/admin/all

Initiate databse creation in correct order.
In priciple this is only POST part of application avilable only for admin to update databse based on two Excel files: Pricelist and OCM.
This way all databse information is based on acctual data.

#/notebook

Lists all notebooks in databse

#/notebook/{id} (get)

List notebook with id indicated in {id}

#/notebook/{id} (post)

Update price of notebook with id indicated in {id}

#/notebook/{id} (delete)

Delete notebook with id indicated in {id} form databse

#/notebook/{id}/warraties

List warraties avilable for notebook with id indicated in {id}

#/notebook/{id}/compontents

List compatible compatible avilable for notebook with id indicated in {id}

#/notebook/{id}/memory

List of compatible RAM memory avilable for notebook with id indicated in {id}

##Project status

Project is still under developnet.
I future it should allow to create order in Excel file and sent it Lenovo distributor in oreder to check stock and possible price fixing.


