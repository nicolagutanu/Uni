# -*- coding: utf-8 -*-
"""
In this file your task is to write the solver function!

"""
def solver(t,w):
    """
    Parameters
    ----------
    t : TYPE: float
        DESCRIPTION: the angle theta
    w : TYPE: float
        DESCRIPTION: the angular speed omega

    Returns
    -------
    F : TYPE: float
        DESCRIPTION: the force that must be applied to the cart
    or
    
    None :if we have a division by zero

    """
    #right, middle, left
    teta = {"NVB": [-55, -40, -25], "NB": [-40, -25, -10], "N": [-20, -10, 0], "ZO": [-5, 0, 5], "P": [0, 10, 20], "PB": [10, 25, 40], "PVB": [25, 20, 55]}
    omega = {"NB": [-11, -7, -3], "N": [-6, -3, 0], "ZO": [-1, 0, 1], "P": [0, 3, 6], "PB": [3, 7, 11]}
    fTable = {"PVB,PB": "PVVB", "PVB,P": "PVVB", "PVB,ZO": "PVB", "PVB,N": "PB", "PVB,NB": "P",
              "PB,PB": "PVVB", "PB,P": "PVB", "PB,ZO": "PB", "PB,N": "P", "PB,NB": "Z",
              "P,PB": "PVB", "P,P": "PB", "P,ZO": "P", "P,N": "Z", "P,NB": "N",
              "ZO,PB": "PB", "ZO,P": "P", "ZO,ZO": "Z", "ZO,N": "N", "ZO,NB": "NB",
              "N,PB": "P", "N,P": "Z", "N,ZO": "N", "N,N": "NB", "N,NB": "NVB",
              "NB,PB": "Z", "NB,P": "N", "NB,ZO": "NB", "NB,N": "NVB", "NB,NB": "NVVB",
              "NVB,PB": "N", "NVB,P": "NB", "NVB,ZO": "NVB", "NVB,N": "NVVB", "NVB,NB": "NVVB"}
    multiply = {"NVVB": -32, "NVB": -24, "NB": -16, "N": -8, "Z": 0, "P": 8, "PB": 16, "PVB": 24,"PVVB": 32}

    membershipDegreeTeta = {"NVB": 0, "NB": 0, "N": 0, "ZO": 0, "P": 0, "PB": 0, "PVB": 0}
    membershipDegreeOmega = {"NB": 0, "N": 0, "ZO": 0, "P": 0, "PB": 0}

    for k in teta.keys():
        if teta[k][0] <= t <= teta[k][2]:
            valueOne = (t - teta[k][0]) / (teta[k][1] - teta[k][0])
            valueTwo = (teta[k][2] - t) / (teta[k][2] - teta[k][1])
            firstValue = min(valueOne, 1, valueTwo)
            membershipDegreeTeta[k] = max(0, firstValue)
        else:
            membershipDegreeTeta[k] = 0

    for key in omega.keys():
        if omega[key][0] <= w <= omega[key][2]:
            valueOne = (w - omega[key][0]) / (omega[key][1] - omega[key][0])
            valueTwo = (omega[key][2] - w) / (omega[key][2] - omega[key][1])
            firstValue = min(valueOne, 1, valueTwo)
            membershipDegreeOmega[key] = max(0, firstValue)
        else:
            membershipDegreeOmega[key] = 0

    membershipDegreeF = {}
    for f in fTable.keys():
        listKeys = f.split(",")
        tetaKey = listKeys[0]
        omegaKey = listKeys[1]
        if fTable[f] not in membershipDegreeF:
            membershipDegreeF[fTable[f]] = []
            membershipDegreeF[fTable[f]].append(min(membershipDegreeTeta[tetaKey], membershipDegreeOmega[omegaKey]))
        else:
            membershipDegreeF[fTable[f]].append(min(membershipDegreeTeta[tetaKey], membershipDegreeOmega[omegaKey]))

    for m in membershipDegreeF.keys():
        while len(membershipDegreeF[m]) > 1:
            membershipDegreeF[m][1] = max(membershipDegreeF[m][0], membershipDegreeF[m][1])
            membershipDegreeF[m].pop(0)

    f = 0
    under = 0
    above = 0
    for d in membershipDegreeF.keys():
        under += membershipDegreeF[d][0]
        above += multiply[d] * membershipDegreeF[d][0]
    if under == 0:
        return None
    else:
        f = above / under
        return f


print(solver(7, -0.5))

