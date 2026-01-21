import org.moqui.context.ExecutionContext

ExecutionContext ec = context.ec

// Validate input

if (!partyId){
    ec.message.addError("partyId is required")
}
if (!firstName){
    ec.message.addError("firstName is required")
}
if (!lastName){
    ec.message.addError("lastName is required")
}

if (ec.message.hasError()) {
    return
}

// Check if  Party exists

def partyExist = ec.entity.find("party.Party")
        .condition("partyId", partyId)


if (!partyExist) {
    ec.message.addError("Party not found with partyId: ${partyId}")
    return
}

// Create Person

def personData = ec.entity.makeValue("party.Person")
personData.setAll(context)
personData.create()

responseMessage = "Person ${firstName} ${lastName} created successfully"
