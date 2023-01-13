import React, { useEffect, useState } from 'react';
import { makeStyles } from '@mui/styles';
import TextField from '@mui/material/TextField';
import { Container ,Paper,Button} from '@mui/material';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: '20px',
        },
    },
}));

export default function Creditcard() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[customerName,setName]=useState('')
    const[cardNumber,setCardnumber]=useState('')
    const[maxLimit,setMaxlimit]=useState('')
    const[cards,setCards]=useState([])
    const classes = useStyles();

    const handleClick=(e)=>{
        e.preventDefault()
        const card={customerName,cardNumber,maxLimit}
        console.log(card)
        fetch("http://localhost:8080/creditcard/add", {
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(card)
        }).then(() => {
            console.log("New Card added")
        })
    }
    
    useEffect(() => {
        fetch("http://127.0.0.1:8080/creditcard/getAll")
        .then(res=>res.json())
        .then((result)=>{
            setCards(result);
        }
    )
    },[])
  return (
    <Container>
        <Paper elevation={3} style={paperStyle}>
            <h1>Add Card</h1>

            <form className={classes.root} noValidate autoComplete="off">
            <TextField id="outlined-basic1" label="Name" variant="outlined" fullWidth 
            value={customerName} 
            onChange={(e)=>setName(e.target.value)}/>
            <br></br>
            <TextField id="outlined-basic2" label="Card Number" variant="outlined" fullWidth 
            value={cardNumber} 
            onChange={(e)=>setCardnumber(e.target.value)}/>
            <br></br>
            <TextField id="outlined-basic3" label="MaxLimit" variant="outlined" fullWidth 
            value={maxLimit} 
            onChange={(e)=>setMaxlimit(e.target.value)}/>
            <br></br>
            <br></br>
            <Button variant="contained" color="secondary" onClick={handleClick}>
                Submit
            </Button>
            </form>
        </Paper>
        <h1>Cards</h1>

      <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Id</TableCell>
            <TableCell>Name</TableCell>
            <TableCell>CardNumber</TableCell>
            <TableCell>balance</TableCell>
            <TableCell>MaxLimit</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {cards.map((card) => (
            <TableRow
              key={card.cardId}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {card.cardId}
              </TableCell>
              <TableCell>{card.customerName}</TableCell>
              <TableCell>{card.cardNumber}</TableCell>
              <TableCell>{card.balance}</TableCell>
              <TableCell>{card.maxLimit}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>

    </Container>
  );
}